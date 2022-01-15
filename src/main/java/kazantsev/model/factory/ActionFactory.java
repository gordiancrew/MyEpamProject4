package kazantsev.model.factory;

import kazantsev.model.actions.Action;

public interface ActionFactory {
    Action createAction(String typeAction);

}
