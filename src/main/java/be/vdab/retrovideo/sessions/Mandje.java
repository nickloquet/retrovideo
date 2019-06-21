package be.vdab.retrovideo.sessions;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Component
@SessionScope
public class Mandje implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Set<Integer> ids = new LinkedHashSet<>();

    public void voegToe(int id){
        ids.add(id);
    }
    public void remove(int id){
        ids.remove(id);
    }
    public boolean bevat(int id){
        return ids.contains(id);
    }
    public boolean isGevuld(){
        return !ids.isEmpty();
    }

    public Set<Integer> getIds() {
        return ids;
    }
}
