package txiner.top.dailytask.util;

import java.io.Serializable;

/**
 * Created by wzhuo on 2016/3/18.
 */
public class Task implements Serializable {
    int time;
    String name;
    String content;
    boolean over;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }
}
