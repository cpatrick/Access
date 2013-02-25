/**
 * 
 */
package org.qibench.access;

import java.util.Iterator;
import java.util.List;

import javax.resource.spi.work.ExecutionContext;

import org.teiid.language.Select;
import org.teiid.translator.DataNotAvailableException;
import org.teiid.translator.ResultSetExecution;
import org.teiid.translator.TranslatorException;

/**
 * @author cpatrick
 *
 */
public class MidasQueryExecution implements ResultSetExecution {

    private Select query;
    private MidasConnection connection;
    private ExecutionContext executionContext;
    
    public MidasQueryExecution(Select command, MidasConnection connection,
            ExecutionContext executionContext) {
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see org.teiid.translator.Execution#cancel()
     */
    @Override
    public void cancel() throws TranslatorException {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.teiid.translator.Execution#close()
     */
    @Override
    public void close() {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.teiid.translator.Execution#execute()
     */
    @Override
    public void execute() throws TranslatorException {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.teiid.translator.ResultSetExecution#next()
     */
    @Override
    public List<?> next() throws TranslatorException, DataNotAvailableException {
        // TODO Auto-generated method stub
        return null;
    }

}
