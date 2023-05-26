package a.eve.newbox.common;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;


public class Copy {
    public static void copy_to_bard(String str){
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(str);
        clipboard.setContent(content);
        Message.msg("复制成功");
    }
}
