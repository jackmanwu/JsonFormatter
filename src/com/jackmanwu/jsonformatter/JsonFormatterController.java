package com.jackmanwu.jsonformatter;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * Created by JackManWu on 2018/1/23.
 */
public class JsonFormatterController {
    private static final String BLANK = "  ";

    @FXML
    private TextArea leftTextArea;

    @FXML
    private TextArea rightTextArea;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void jsonFormatAction() {
        String json = leftTextArea.getText();
        if (json.equals("")) {
            return;
        }
        rightTextArea.setText(jsonFormat(json));
    }

    /**
     * 格式化json
     *
     * @param json
     * @return
     */
    private String jsonFormat(String json) {
        StringBuffer result = new StringBuffer();
        int len = json.length();
        int num = 0;
        for (int i = 0; i < len; i++) {
            char current = json.charAt(i);
            if ((current == '{') || (current == '[')) {
                if (i > 0 && json.charAt(i - 1) == ':') {
                    result.append('\n');
                    blank(result, num);
                }
                result.append(current);
                result.append('\n');
                num++;
                blank(result, num);
                continue;
            }
            if ((current == '}') || (current == ']')) {
                result.append('\n');
                num--;
                blank(result, num);
                result.append(current);
                if ((i < len - 1) && (json.charAt(i + 1) != ',')) {
                    result.append('\n');
                }
                continue;
            }
            if (current == ',') {
                result.append(current);
                result.append('\n');
                blank(result, num);
                continue;
            }
            result.append(current);
        }
        return result.toString();
    }

    private void blank(StringBuffer result, int num) {
        for (int i = 0; i < num; i++) {
            result.append(BLANK);
        }
    }
}
