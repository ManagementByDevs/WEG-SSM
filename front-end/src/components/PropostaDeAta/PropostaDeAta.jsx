import React, { useState } from "react";

import { Box, Typography } from "@mui/material";

import FontConfig from "../../service/FontConfig";

const PropostaDeAta = (props) => {

    return (
        <Box>
            {/* início da proposta */}
            <Box className="flex justify-center align-center">
                <Typography fontSize={FontConfig.title} fontWeight={600}>
                    Proposta 01
                </Typography>
            </Box>

            <Box>
                <Typography
                    fontSize={FontConfig.veryBig}
                    fontWeight="600"
                    color="text.primary"
                >
                    Título da Proposta:
                </Typography>
                <Typography
                    fontSize={FontConfig.medium}
                    className="text-justify"
                    color="text.secondary"
                    sx={{ marginLeft: "30px" }}
                >
                    Aqui vai um exemplo de título
                </Typography>
            </Box>

            <Box>
                <Typography
                    fontSize={FontConfig.veryBig}
                    fontWeight="600"
                    color="text.primary"
                >
                    Problema:
                </Typography>
                <Typography
                    fontSize={FontConfig.medium}
                    className="text-justify"
                    color="text.secondary"
                    sx={{ marginLeft: "30px" }}
                >
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptate cupiditate, ullam consequuntur recusandae consequatur natus obcaecati laborum officia cum, eum aliquam voluptatibus quas accusantium quo quis autem adipisci. Numquam, illum!
                </Typography>
            </Box>

            <Box>
                <Typography
                    fontSize={FontConfig.veryBig}
                    fontWeight="600"
                    color="text.primary"
                >
                    Proposta:
                </Typography>
                <Typography
                    fontSize={FontConfig.medium}
                    className="text-justify"
                    color="text.secondary"
                    sx={{ marginLeft: "30px" }}
                >
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptatum quae illum quibusdam commodi sint numquam officiis molestiae ipsam minus, optio aliquam, quaerat nisi in nulla itaque, voluptatem vel voluptate illo.
                </Typography>
            </Box>

            <Box>
                <Typography
                    fontSize={FontConfig.veryBig}
                    fontWeight="600"
                    color="text.primary"
                >
                    Benefícios:
                </Typography>
                <Typography
                    fontSize={FontConfig.medium}
                    className="text-justify"
                    color="text.secondary"
                    sx={{ marginLeft: "30px" }}
                >
                    Benefícios
                </Typography>
            </Box>

            <Box>
                <Typography
                    fontSize={FontConfig.veryBig}
                    fontWeight="600"
                    color="text.primary"
                >
                    Frequência de Uso:
                </Typography>
                <Typography
                    fontSize={FontConfig.medium}
                    className="text-justify"
                    color="text.secondary"
                    sx={{ marginLeft: "30px" }}
                >
                    Frequência
                </Typography>
            </Box>

            <Box>
                <Typography
                    fontSize={FontConfig.veryBig}
                    fontWeight="600"
                    color="text.primary"
                >
                    Anexos:
                </Typography>
                <Typography
                    fontSize={FontConfig.medium}
                    className="text-justify"
                    color="text.secondary"
                    sx={{ marginLeft: "30px" }}
                >
                    Anexos
                </Typography>
            </Box>
        </Box>
    );
}

export default PropostaDeAta;