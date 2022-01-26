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
    final static String ADMIN="/library/admin";
    final static String DELETEBOOK="/library/deletebook";
    final static String ADDBOOK="/library/addbook";
    final static String RULES="/library/rules";
    final static String NONCONFIRMREADERS="/library/nonconfirmreaders";
    final static String DELETEUSER="/library/deleteuser";
    final static String USER="/library/user";
    final static String CONFIRMREADER="/library/confirmreader";
    final static String READERS="/library/readers";

    @Override
    public Action createAction(String typeAction) {
        if (typeAction.equals(BOOKS)) {
            return new ActionBooks();
        } else if (typeAction.equals(LANGUAGE)) {
            return new ActionLanguage();
        } else if (typeAction.equals(CONFIRMREADER)) {
            return new ActionConfirmReader();
        } else if (typeAction.equals(USER)) {
            return new ActionUser();
        } else if (typeAction.equals(READERS)) {
            return new ActionReaders();
        } else if (typeAction.equals(DELETEUSER)) {
            return new ActionDeleteUser();
        } else if (typeAction.equals(NONCONFIRMREADERS)) {
            return new ActionNonConfirmReaders();
        } else if (typeAction.equals(RULES)) {
            return new ActionRules();
        } else if (typeAction.equals(ADDBOOK)) {
            return new ActionAddBook();
        } else if (typeAction.equals(ADMIN)) {
            return new ActionAdmin();
        } else if (typeAction.equals(DELETEBOOK)) {
            return new ActionDeleteBook();
        } else if (typeAction.equals(BOOKRETURN)) {
            return new ActionBookReturn();
        } else if (typeAction.equals(BOOK)) {
            return new ActionBook();
        } else if (typeAction.equals(LOGOUT)) {
            return new ActionLogout();
        } else if (typeAction.equals(REGISTRATION)) {
            return new ActionRegistration();
        } else if (typeAction.equals(BOOKSBOX)) {
            return new ActionBooksBox();
        } else {
            return new ActionHome();
        }
    }
}
