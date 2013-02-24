/**
 * 
 */
package org.qibench.access;

import javax.resource.ResourceException;

import org.teiid.resource.spi.BasicConnection;

/**
 * @author cpatrick
 *
 */
public class MidasConnection extends BasicConnection {

    /**
     * 
     */
    String userName;
    String userPassword;
    String hostName;

    /**
     * 
     */
    public MidasConnection(String user, String pass, String host) {
        this.userName = user;
        this.userPassword = pass;
        this.hostName = host;
    }

    /* (non-Javadoc)
     * @see javax.resource.cci.Connection#close()
     */
    @Override
    public void close() throws ResourceException {
        // TODO Auto-generated method stub

    }

}
