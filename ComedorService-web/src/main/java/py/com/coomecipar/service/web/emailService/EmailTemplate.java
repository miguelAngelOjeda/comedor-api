/**
 *
 */
package py.com.coomecipar.service.web.emailService;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import py.com.coomecipar.service.ejb.utils.AppUtil;
import py.com.coomecipar.service.ejb.utils.ApplicationLogger;
import py.com.coomecipar.service.ejb.utils.Constants;

/**
 * @author pavan.solapure
 *
 */
public class EmailTemplate {

    private static final ApplicationLogger logger = ApplicationLogger.getInstance();

    private String templateId;

    private String template;

    private Map<String, String> replacementParams;

    public EmailTemplate(String templateId) {
        this.templateId = templateId;
        try {
            this.template = loadTemplate(templateId);
        } catch (Exception e) {
            this.template = Constants.BLANK;
        }
    }

    private String loadTemplate(String templateId) throws Exception {

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream file = classLoader.getResourceAsStream("/email-templates/" + templateId);
        String content = Constants.BLANK;
        if(file != null){
            content = new BufferedReader(new InputStreamReader(file))
                .lines().collect(Collectors.joining("\n"));
        }
        
        //content = new String(Files.readAllBytes(file.toPath()));
        return content;
    }

    public String getTemplate(Map<String, String> replacements) {
        String cTemplate = this.getTemplate();

        if (!AppUtil.isObjectEmpty(cTemplate)) {
            for (Map.Entry<String, String> entry : replacements.entrySet()) {
                cTemplate = cTemplate.replace("{{" + entry.getKey() + "}}", entry.getValue());
            }
        }

        return cTemplate;
    }

    /**
     * @return the templateId
     */
    public String getTemplateId() {
        return templateId;
    }

    /**
     * @param templateId the templateId to set
     */
    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    /**
     * @return the template
     */
    public String getTemplate() {
        return template;
    }

    /**
     * @param template the template to set
     */
    public void setTemplate(String template) {
        this.template = template;
    }

    /**
     * @return the replacementParams
     */
    public Map<String, String> getReplacementParams() {
        return replacementParams;
    }

    /**
     * @param replacementParams the replacementParams to set
     */
    public void setReplacementParams(Map<String, String> replacementParams) {
        this.replacementParams = replacementParams;
    }

}
