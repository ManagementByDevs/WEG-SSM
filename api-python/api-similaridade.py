import mysql.connector
import spacy
from flask import Flask, jsonify, request
from flask_cors import CORS

# criando o objeto flask
app = Flask(__name__)
CORS(app)

# carregamento o modelo de linguagem em português
nlp = spacy.load("pt_core_news_sm")

# configurações de acesso do mysql
mysql_config = {
    'host': 'localhost',
    'user': 'root',
    'password': 'root',
    'database': 'wegssm',
}

# definindo o que seria algo similar
limite_similaridade = 0.6

# função para retornar uma demanda que seja similar
@app.route('/api/similaridade', methods=['POST'])
def calcular_similaridade():
    # busca os dados enviados pelo usuário
    data = request.json

    # pega os campos do json
    titulo = data['titulo']
    problema = data['problema']
    proposta = data['proposta']

    # Conecta ao banco de dados MySQL
    conn = mysql.connector.connect(**mysql_config)

    # cria um cursor para executar as consultas
    cursor = conn.cursor()

    # Realiza uma consulta SQL para obter os campos das demandas da base de dados
    cursor.execute("SELECT id, titulo, problema_consulta, proposta_consulta FROM demanda")
    # Armazena os resultados da consulta
    resultados = cursor.fetchall()

    # Calcula a similaridade entre os campos das demandas da base de dados e os campos fornecidos
    similaridades = []

    # laço de repetição para percorrer os resultados
    for resultado in resultados:
        # pega o id e os campos da demanda
        id_demanda = resultado[0]
        titulo_demanda = resultado[1]
        problema_demanda = resultado[2]
        proposta_demanda = resultado[3]

        # calcula a similaridade entre cada campo da demanda e o campo fornecido
        similaridade_titulo = nlp(titulo).similarity(nlp(titulo_demanda))
        similaridade_problema = nlp(problema).similarity(nlp(problema_demanda))
        similaridade_proposta = nlp(proposta).similarity(nlp(proposta_demanda))

        # verifica se a média das similaridades dos três campos é acima do limite
        similaridade_media = (similaridade_titulo + similaridade_problema + similaridade_proposta) / 3
        if similaridade_media >= limite_similaridade:
            similaridades.append((id_demanda, similaridade_media))
        

    # Ordena a lista de similaridades com base na similaridade
    similaridades.sort(key=lambda x: x[1], reverse=True)

    # Fecha a conexão com o banco de dados
    cursor.close()
    conn.close()

    # Verifica se há demandas similares e retorna o ID da demanda mais similar ou uma mensagem de nenhum resultado
    if similaridades:
        id_demanda_similar = similaridades[0][0]
        return jsonify({'id_demanda_similar': id_demanda_similar})
    else:
        return jsonify({'message': 'Nenhuma demanda similar encontrada.'})

# rodando o servidor
if __name__ == "__main__":
    app.run()