package dmnlukasik.ejb;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
/*
 * The roles that are to be used from within the code when invoking
 * isCallerInRole need to be declared using the @DeclareRoles annotation,
 * otherwise an exception will be thrown when calling isCallerInRole.
 */
@DeclareRoles({"superusers", "plainusers"})
/*
 * When applied at class-level, the @RolesAllowed annotation specifies
 * which security-roles are allowed to access all the methods in the
 * EJB. @RolesAllowed may also be used at method-level.
 */
@RolesAllowed({"superusers", "plainusers"})
@RunAs("runasadmin")
public class StatelessSession1Bean extends CommonStatelessSessionBean {

    @EJB
    private StatelessSession2Bean bean2;

    public String greeting(final String name) {
        System.out.println("*** StatelessSession1Bean.greeting");
        printSecurityInfo();
        try {
            System.out.println("    Message for the superuser: " + bean2.superusersOnly());
        } catch (Throwable theException) {
            System.out.println("    No message for the superuser.");
        }
        String greetingFromBean2 = bean2.greeting(name);
        return assembleGreeting(name, "StatelessSession1Bean") + "\n" + greetingFromBean2;
    }
}