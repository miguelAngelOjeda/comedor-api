/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.coomecipar.service.web.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.mail.Session;
import java.util.Properties;
import javax.servlet.http.HttpServletResponse;

import org.apache.sshd.ClientSession;
import org.apache.sshd.SshClient;
import org.apache.sshd.client.SftpClient;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import py.com.coomecipar.service.ejb.utils.ApplicationLogger;
import py.com.coomecipar.service.web.utils.GuardarPropuestaRequest;
import py.com.coomecipar.service.web.utils.SagServiceDTO;

/**
 *
 * @author miguel.ojeda
 */
@Controller
@RequestMapping(value = "/service")
public class ServiceController extends BaseController {

    private static final ApplicationLogger logger = ApplicationLogger.getInstance();

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    SagServiceDTO sagService(@RequestBody String peticionStr, HttpServletResponse response) {
        SagServiceDTO retorno = new SagServiceDTO();
        try {
            Gson gson = new GsonBuilder().create();
            
            logger.info("REQUEST SERVER: " + peticionStr);
            GuardarPropuestaRequest request = gson.fromJson(peticionStr,
                    GuardarPropuestaRequest.class);

            SshClient client = SshClient.setUpDefaultClient();
            client.start();

            final ClientSession session = client.connect(request.getServer(), 22).await().getSession();
            session.authPassword(request.getUserSftp(), request.getPassSftp());
            //session.wait(10000);

            SftpClient clien = session.createSftpClient();

            InputStream input = clien.read(request.getPath() + request.getNombreArchivo().replace(".pdf", ""));

            request.setArchivo(Base64.encodeBase64String(getBytesFromInputStream(input)));

            String url = "http://172.16.1.51:9191/" + "GestorDeProcesos-web/webresources/documentos/service/sag";

            URL restServiceURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) restServiceURL.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            
            logger.info("REQUEST: " + gson.toJson(request));
            
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(gson.toJson(request).getBytes());
            outputStream.flush();

            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            String output;
            StringBuilder sb = new StringBuilder();
            while ((output = responseBuffer.readLine()) != null) {
                sb.append(output);
            }
            
            retorno = gson.fromJson(sb.toString(), SagServiceDTO.class);
            
            if(retorno.getEstado() >=0){
                response.setStatus(200);
            }else{
                response.setStatus(300);
            }
            
        } catch (Exception e) {
            response.setStatus(300);
            System.out.println("Error " + e);
            retorno.setEstado(-1);
        }

        return retorno;

    }
    

    @RequestMapping(value = "/sag/cancelar", method = RequestMethod.GET)
    public @ResponseBody
    SagServiceDTO desactivar(@RequestBody String peticionStr,
            HttpServletResponse response) {
        SagServiceDTO retorno = new SagServiceDTO();
        try {
            String url = "http://172.16.1.51:9191/" + "GestorDeProcesos-web/webresources/documentos/service/sag/desactivar";
            
            Gson gson = new GsonBuilder().create();
            
            logger.info("REQUEST SERVER: " + peticionStr);
            GuardarPropuestaRequest request = gson.fromJson(peticionStr,
                    GuardarPropuestaRequest.class);
            
            URL restServiceURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) restServiceURL.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            
            logger.info("REQUEST: " + gson.toJson(request));
            
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(gson.toJson(request).getBytes());
            outputStream.flush();

            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            String output;
            StringBuilder sb = new StringBuilder();
            while ((output = responseBuffer.readLine()) != null) {
                sb.append(output);
            }
            
            retorno = gson.fromJson(sb.toString(), SagServiceDTO.class);
            
            if(retorno.getEstado() >=0){
                response.setStatus(200);
            }else{
                response.setStatus(300);
            }
            
        } catch (Exception e) {
            response.setStatus(300);
            logger.error("Error al desactivar registros", e);
        }

        return retorno;
    }

    public static byte[] getBytesFromInputStream(InputStream is) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[0xFFFF];
        for (int len = is.read(buffer); len != -1; len = is.read(buffer)) {
            os.write(buffer, 0, len);
        }
        return os.toByteArray();
    }

    private static String encodeFileToBase64Binary(File file) {
        String encodedfile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return encodedfile;
    }

}
