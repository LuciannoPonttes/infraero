﻿----------------------------------------------------------------------
--	SCRIPT: ACRESCENTA CONFIGURACAO PARA DEFINIR AUTOMATICAMENTE PAPEL
----------------------------------------------------------------------
ALTER SESSION SET CURRENT_SCHEMA=corporativo;

Insert into CORPORATIVO.CP_TIPO_CONFIGURACAO (ID_TP_CONFIGURACAO,DSC_TP_CONFIGURACAO,ID_SIT_CONFIGURACAO) values (43,'Juntada Automática', 5);