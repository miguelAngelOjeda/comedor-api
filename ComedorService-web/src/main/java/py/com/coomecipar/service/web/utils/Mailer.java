package py.com.ncode.coomecipar.mailer.util;

import java.io.File;
import java.security.Security;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mailer {

//    private static final String EMAIL_ATOM = "[^\\x00-\\x1F^\\(^\\)^\\<^\\>^\\@^\\,^\\;^\\:^\\\\^\\\"^\\.^\\[^\\]^\\s]";
//    private static final String EMAIL_DOMAIN = "(" + EMAIL_ATOM + "+(\\." + EMAIL_ATOM + "+)*";
//    private static final String EMAIL_IP_DOMAIN = "\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\]";
//    private static final Pattern EMAIL_PATTERN = Pattern.compile("^" + EMAIL_ATOM + "+(\\." + EMAIL_ATOM + "+)*@" + EMAIL_DOMAIN + "|" + EMAIL_IP_DOMAIN + ")$", Pattern.CASE_INSENSITIVE);
    private String host;
    private int puerto;
    private boolean ssl;
    private boolean autenticacion;
    private String cuenta;
    private String usuario;
    private String password;
    private String aliasFrom;

    public Mailer() {
        inicializar();
    }

    private void inicializar() {
        host = "";
        puerto = 0;
        ssl = false;
        autenticacion = true;
        cuenta = "";
        usuario = "";
        password = "";
        aliasFrom = "";
    }

    public boolean enviarEmailSimple(String asunto, String contenido, String destinatario) {
        String[] destinatarios = new String[1];
        destinatarios[0] = destinatario;
        return enviarEmailSimple(asunto, contenido, destinatarios);
    }

    public boolean enviarEmailSimple(String asunto, String contenido, String[] destinatarios) {
        return enviarEmailSimple(asunto, contenido, destinatarios, null);
    }

    public boolean enviarEmailSimple(String asunto, String contenido, String[] destinatarios, String[] destinatariosCC) {
        return enviarEmailSimple(asunto, contenido, destinatarios, destinatariosCC, null);
    }

    public boolean enviarEmailSimple(String asunto, String contenido, String[] destinatarios, String[] destinatariosCC, String[] destinatariosBCC) {
        if (!host.equalsIgnoreCase("")) {
            try {
                // Validamos que por lo menos tenga un destinatario
                boolean tieneDest = false;
                if ((destinatarios != null) && (destinatarios.length > 0)) {
                    tieneDest = true;
                }
                if ((destinatariosCC != null) && (destinatariosCC.length > 0)) {
                    tieneDest = true;
                }
                if ((destinatariosBCC != null) && (destinatariosBCC.length > 0)) {
                    tieneDest = true;
                }

                if (tieneDest) {
                    if (ssl) {
//                        Security.addProvider(new Provider());
                        Security.addProvider(Security.getProvider("SunJSSE"));
                    }

                    // Preparamos los parametros
                    Properties props = new Properties();
                    props.setProperty("mail.transport.protocol", "smtp");
                    props.setProperty("mail.smtp.auth", String.valueOf(autenticacion));
                    props.setProperty("mail.smtp.host", host);
                    props.setProperty("mail.smtp.port", String.valueOf(puerto));
                    if (ssl) {
                        props.setProperty("mail.smtp.starttls.enable", "true");
                    }

                    // Creamos la sesion de mail y el transporte
                    Session mailSession;
                    if (autenticacion) {
                        PwdAuthenticator pwdA = new PwdAuthenticator(usuario, password);
                        mailSession = Session.getInstance(props, pwdA);
                    } else {
                        mailSession = Session.getInstance(props);
                    }
                    Transport mailTransport = mailSession.getTransport();

                    // Preparamos los destinatarios
                    InternetAddress[] dest = null;
                    InternetAddress[] destCC = null;
                    InternetAddress[] destBCC = null;
                    if ((destinatarios != null) && (destinatarios.length > 0)) {
                        dest = new InternetAddress[destinatarios.length];
                        for (int i = 0; i < destinatarios.length; i++) {
                            dest[i] = new InternetAddress(destinatarios[i]);
                        }
                    }
                    if ((destinatariosCC != null) && (destinatariosCC.length > 0)) {
                        destCC = new InternetAddress[destinatariosCC.length];
                        for (int i = 0; i < destinatariosCC.length; i++) {
                            destCC[i] = new InternetAddress(destinatariosCC[i]);
                        }
                    }
                    if ((destinatariosBCC != null) && (destinatariosBCC.length > 0)) {
                        destBCC = new InternetAddress[destinatariosBCC.length];
                        for (int i = 0; i < destinatariosBCC.length; i++) {
                            destBCC[i] = new InternetAddress(destinatariosBCC[i]);
                        }
                    }

                    // Creamos el mensaje
                    MimeMessage mensaje = new MimeMessage(mailSession);
                    mensaje.setFrom(new InternetAddress(cuenta, aliasFrom));
                    mensaje.setSubject(asunto);
                    mensaje.setContent(contenido, "text/plain");
                    if (dest != null) {
                        mensaje.addRecipients(Message.RecipientType.TO, dest);
                    }
                    if (destCC != null) {
                        mensaje.addRecipients(Message.RecipientType.CC, destCC);
                    }
                    if (destBCC != null) {
                        mensaje.addRecipients(Message.RecipientType.BCC, destBCC);
                    }

                    // Abrimos el transporte y enviamos el mensaje
                    mailTransport.connect();
                    if (dest != null) {
                        mailTransport.sendMessage(mensaje, mensaje.getRecipients(Message.RecipientType.TO));
                    }
                    if (destCC != null) {
                        mailTransport.sendMessage(mensaje, mensaje.getRecipients(Message.RecipientType.CC));
                    }
                    if (destBCC != null) {
                        mailTransport.sendMessage(mensaje, mensaje.getRecipients(Message.RecipientType.BCC));
                    }
                    mailTransport.close();

                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean enviarEmailHtml(String asunto, String contenido, String destinatario) {
        return enviarEmailHtml(asunto, contenido, null, destinatario);
    }

    public boolean enviarEmailHtml(String asunto, String contenido, File[] adjuntos, String destinatario) {
        String[] destinatarios = new String[1];
        destinatarios[0] = destinatario;
        return enviarEmailHtml(asunto, contenido, adjuntos, destinatarios);
    }

    public boolean enviarEmailHtml(String asunto, String contenido, File[] adjuntos, String[] destinatarios) {
        return enviarEmailHtml(asunto, contenido, adjuntos, destinatarios, null);
    }

    public boolean enviarEmailHtml(String asunto, String contenido, File[] adjuntos, String[] destinatarios, String[] destinatariosCC) {
        return enviarEmailHtml(asunto, contenido, adjuntos, destinatarios, destinatariosCC, null);
    }

    public boolean enviarEmailHtml(String asunto, String contenido, File[] adjuntos, String[] destinatarios, String[] destinatariosCC, String[] destinatariosBCC) {
        if (!host.equalsIgnoreCase("")) {
            try {
                // Validamos que por lo menos tenga un destinatario
                boolean tieneDest = false;
                if ((destinatarios != null) && (destinatarios.length > 0) && (destinatarios[0] != null)) {
                    tieneDest = true;
                }
                if ((destinatariosCC != null) && (destinatariosCC.length > 0) && (destinatariosCC[0] != null)) {
                    tieneDest = true;
                }
                if ((destinatariosBCC != null) && (destinatariosBCC.length > 0) && (destinatariosBCC[0] != null)) {
                    tieneDest = true;
                }

                if (tieneDest) {
                    if (ssl) {
//                        Security.addProvider(new Provider());
                        Security.addProvider(Security.getProvider("SunJSSE"));
                    }

                    // Preparamos los parametros
                    Properties props = new Properties();
                    props.setProperty("mail.transport.protocol", "smtp");
                    props.setProperty("mail.smtp.auth", String.valueOf(autenticacion));
                    props.setProperty("mail.smtp.host", host);
                    props.setProperty("mail.smtp.port", String.valueOf(puerto));
                    if (ssl) {
                        props.setProperty("mail.smtp.starttls.enable", "true");
                    }

                    // Creamos la sesion de mail y el transporte
                    Session mailSession;
                    if (autenticacion) {
                        PwdAuthenticator pwdA = new PwdAuthenticator(usuario, password);
                        mailSession = Session.getInstance(props, pwdA);
                    } else {
                        mailSession = Session.getInstance(props);
                    }
                    Transport mailTransport = mailSession.getTransport();

                    // Preparamos los destinatarios
                    InternetAddress[] dest = null;
                    InternetAddress[] destCC = null;
                    InternetAddress[] destBCC = null;
                    if ((destinatarios != null) && (destinatarios.length > 0) && (destinatarios[0] != null)) {
                        dest = new InternetAddress[destinatarios.length];
                        for (int i = 0; i < destinatarios.length; i++) {
                            dest[i] = new InternetAddress(destinatarios[i]);
                        }
                    }
                    if ((destinatariosCC != null) && (destinatariosCC.length > 0) && (destinatariosCC[0] != null)) {
                        destCC = new InternetAddress[destinatariosCC.length];
                        for (int i = 0; i < destinatariosCC.length; i++) {
                            destCC[i] = new InternetAddress(destinatariosCC[i]);
                        }
                    }
                    if ((destinatariosBCC != null) && (destinatariosBCC.length > 0) && (destinatariosBCC[0] != null)) {
                        destBCC = new InternetAddress[destinatariosBCC.length];
                        for (int i = 0; i < destinatariosBCC.length; i++) {
                            destBCC[i] = new InternetAddress(destinatariosBCC[i]);
                        }
                    }

                    // Creamos el mensaje
                    MimeMessage mensaje = new MimeMessage(mailSession);
                    mensaje.setFrom(new InternetAddress(cuenta, aliasFrom));
                    mensaje.setSubject(asunto);
                    if (dest != null) {
                        mensaje.addRecipients(Message.RecipientType.TO, dest);
                    }
                    if (destCC != null) {
                        mensaje.addRecipients(Message.RecipientType.CC, destCC);
                    }
                    if (destBCC != null) {
                        mensaje.addRecipients(Message.RecipientType.BCC, destBCC);
                    }

                    // El mail HTML tiene 2 partes (multipart), el contenido y los adjuntos
                    MimeMultipart multipart = new MimeMultipart("related");

                    // El contenido
                    // Ejemplo de contenido (con imagen como adjunto) "<H1>Hello</H1><img src=\"cid:adj_0\">"
                    BodyPart parteMensaje = new MimeBodyPart();
                    parteMensaje.setContent(contenido, "text/html");

                    // Se agrega el contenido
                    multipart.addBodyPart(parteMensaje);

                    // Los adjuntos
                    if (adjuntos != null) {
                        for (int i = 0; i < adjuntos.length; i++) {
                            parteMensaje = new MimeBodyPart();
                            parteMensaje.setDataHandler(new DataHandler(new FileDataSource(adjuntos[i])));
                            parteMensaje.setHeader("Content-ID", "<adj_" + i + ">");
                            parteMensaje.setFileName(adjuntos[i].getName());

                            // Se agrega el adjunto
                            multipart.addBodyPart(parteMensaje);
                        }
                    }

                    // Se agrega el multipart al mensaje como contenido
                    mensaje.setContent(multipart);
                    
                    // Abrimos el transporte y enviamos el mensaje
                    mailTransport.connect();
                    if (dest != null) {
                        mailTransport.sendMessage(mensaje, mensaje.getRecipients(Message.RecipientType.TO));
                    }
                    if (destCC != null) {
                        mailTransport.sendMessage(mensaje, mensaje.getRecipients(Message.RecipientType.CC));
                    }
                    if (destBCC != null) {
                        mailTransport.sendMessage(mensaje, mensaje.getRecipients(Message.RecipientType.BCC));
                    }
                    mailTransport.close();

                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }
    
    public String getHtmlContenido(String asunto, String mensaje, List<String> detalles) {
        String message = "<i>Greetings!</i><br>";
        message += "<b>Wish you a nice day!</b><br>";
        message += "<font color=red>Duke</font>";
        
        return null;
        
    }
    public String getAliasFrom() {
        return aliasFrom;
    }

    public void setAliasFrom(String aliasFrom) {
        this.aliasFrom = aliasFrom;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public boolean isSsl() {
        return ssl;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }

    public boolean isAutenticacion() {
        return autenticacion;
    }

    public void setAutenticacion(boolean autenticacion) {
        this.autenticacion = autenticacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public static boolean isValidEmailAddress(String emailAddress) {
        String expression = "^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = emailAddress;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();

//        if (emailAddress == null || emailAddress.length() == 0) {
//            return true;
//        }
//        Matcher m = EMAIL_PATTERN.matcher(emailAddress);
//        return m.matches();
    }
}

class PwdAuthenticator extends Authenticator {

    String usuario;
    String password;

    public PwdAuthenticator(String usuario, String password) {
        super();
        this.usuario = usuario;
        this.password = password;
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(usuario, password);
    }
}