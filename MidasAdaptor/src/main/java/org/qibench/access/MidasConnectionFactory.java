/**
 * 
 */
package org.qibench.access;

import javax.resource.ResourceException;

import org.teiid.resource.spi.BasicConnectionFactory;

/**
 * @author cpatrick
 *
 */
public class MidasConnectionFactory extends BasicConnectionFactory<MidasConnection> {

    /**
     * 
     */
    private static final long serialVersionUID = -2563832185756337325L;

    private String userName;
    private String userPassword;
    private String hostName;
    
    public MidasConnectionFactory(String user, String pass, String host) {
        super();
        this.userName = user;
        this.userPassword = pass;
        this.hostName = host;
    }

    @Override
    public MidasConnection getConnection() throws ResourceException {
        return new MidasConnection(this.userName, this.userPassword, this.hostName);
    }

}
