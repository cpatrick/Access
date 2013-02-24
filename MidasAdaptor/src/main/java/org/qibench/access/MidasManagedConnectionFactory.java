/**
 * 
 */
package org.qibench.access;

import javax.resource.ResourceException;

import org.teiid.resource.spi.BasicConnectionFactory;
import org.teiid.resource.spi.BasicManagedConnectionFactory;

/**
 * @author cpatrick
 *
 */
public class MidasManagedConnectionFactory extends BasicManagedConnectionFactory {

    /**
     * Serial Version UID for java serialization 
     */
    private static final long serialVersionUID = -2099016721846843318L;

    /**
     * The user name that will be used to authenticate against the Midas instance
     */
    String userName;

    /**
     * The password that will be used to authenticate against the Midas instance
     */
    String userPassword;

    /**
     * The hostname of the Midas instance targeted.
     */
    String hostName;

    /**
     * Default contsturctor
     */
    public MidasManagedConnectionFactory() {
        // TODO Auto-generated constructor stub
    }
    
    

    /* (non-Javadoc)
     * @see org.teiid.resource.spi.BasicManagedConnectionFactory#createConnectionFactory()
     */
    @Override
    public BasicConnectionFactory<?> createConnectionFactory()
            throws ResourceException {
        return new MidasConnectionFactory(this.getUserName(), this.getUserPassword(),
                this.getHostName());
    }

    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String name) {
        this.userName = name;
    }


    public String getUserPassword() {
        return this.userPassword;
    }
    public void setUserPassword(String pass) {
        this.userPassword = pass;
    }

    public String getHostName() {
        return this.hostName;
    }
    public void setHostName(String host) {
        this.hostName = host;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return true;
    }

}
