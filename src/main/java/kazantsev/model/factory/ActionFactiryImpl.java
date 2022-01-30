package kazantsev.model.factory;

import kazantsev.model.actions.Action;
import kazantsev.model.actions.actionsimpl.*;

public class ActionFactiryImpl implements ActionFactory {
    final static String BOOKS = "/library/books";
    final static String LANGUAGE = "/library/language";
    final static String BOOK = "/library/book";
    final static String LOGOUT = "/library/logout";
    final static String REGISTRATION = "/library/registration";
    final static String BOOKSBOX = "/library/booksbox";
    final static String BOOKRETURN = "/library/bookreturn";
    final static String DELETEBOOK = "/library/deletebook";
    final static String ADDBOOK = "/library/addbook";
    final static String RULES = "/library/rules";
    final static String NONCONFIRMREADERS = "/library/nonconfirmreaders";
    final static String DELETEUSER = "/library/deleteuser";
    final static String USER = "/library/user";
    final static String CONFIRMREADER = "/library/confirmreader";
    final static String READERS = "/library/readers";

    @Override
    public Action createAction(String typeAction) {
        Action action = null;
        switch (typeAction) {
            case BOOKS:
                action = new ActionBooks();
                break;
            case LANGUAGE:
                action = new ActionLanguage();
                break;
            case CONFIRMREADER:
                action = new ActionConfirmReader();
                break;
            case USER:
                action = new ActionUser();
                break;
            case READERS:
                action = new ActionReaders();
                break;
            case DELETEUSER:
                action = new ActionDeleteUser();
                break;
            case NONCONFIRMREADERS:
                action = new ActionNonConfirmReaders();
                break;
            case RULES:
                action = new ActionRules();
                break;
            case ADDBOOK:
                action = new ActionAddBook();
                break;
            case DELETEBOOK:
                action = new ActionDeleteBook();
                break;
            case BOOKRETURN:
                action = new ActionBookReturn();
                break;
            case BOOK:
                action = new ActionBook();
                break;
            case LOGOUT:
                action = new ActionLogout();
                break;
            case REGISTRATION:
                action = new ActionRegistration();
                break;
            case BOOKSBOX:
                action = new ActionBooksBox();
                break;
            default:
                action = new ActionHome();
                break;
        }
        return action;
    }
}
