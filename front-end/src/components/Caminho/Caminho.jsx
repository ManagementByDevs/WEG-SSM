import { React, useState, useContext } from "react";
import { useLocation, useNavigate } from "react-router-dom";

import { Typography, Box, Tooltip } from "@mui/material";
import HomeOutlinedIcon from "@mui/icons-material/HomeOutlined";
import ArrowForwardIosOutlinedIcon from "@mui/icons-material/ArrowForwardIosOutlined";

import FontConfig from "../../service/FontConfig";

import FontContext from "../../service/FontContext";

const Caminho = (props) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);
  
  const navigate = useNavigate();

  const caminhoURL = useLocation().pathname;

  const listaCaminho = caminhoURL.split("/");

  const getPathName = (item) => {
    item = item.charAt(0).toUpperCase() + item.slice(1);
    return item.replaceAll("-", " ");
  };

  return (
    <Box className="flex items-center gap-x-1" color="link.main">
      <Tooltip title="Home">
        <HomeOutlinedIcon
          className="cursor-pointer"
          sx={{ fontSize: "32px" }}
          onClick={() => {
            navigate("/");
          }}
        />
      </Tooltip>
      <ArrowForwardIosOutlinedIcon sx={{ fontSize: "20px" }} />
      {listaCaminho.map((item, index) => {
        if (item !== "") {
          return (
            <Box key={index}>
              {!props.feedback ? (
                <Typography
                  className="cursor-pointer"
                  fontSize={FontConfig.default}
                  sx={{ fontWeight: 500 }}
                  onClick={() => {
                    navigate("/" + item);
                  }}
                >
                  {getPathName(item)}
                </Typography>
              ) : (
                <Typography
                  className="cursor-pointer"
                  fontSize={FontConfig.default}
                  sx={{ fontWeight: 500 }}
                >
                  {getPathName(item)}
                </Typography>
              )}
            </Box>
          );
        }
      })}
    </Box>
  );
};

export default Caminho;
