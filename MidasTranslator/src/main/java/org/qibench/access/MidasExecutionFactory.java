/**
 * 
 */
package org.qibench.access;

import javax.resource.spi.work.ExecutionContext;

import org.teiid.core.BundleUtil;
import org.teiid.language.QueryExpression;
import org.teiid.language.Select;
import org.teiid.logging.LogConstants;
import org.teiid.logging.LogManager;
import org.teiid.metadata.RuntimeMetadata;
import org.teiid.translator.ExecutionFactory;
import org.teiid.translator.ResultSetExecution;
import org.teiid.translator.Translator;
import org.teiid.translator.TranslatorException;
import org.qibench.access.MidasConnectionFactory;
import org.qibench.access.MidasConnection;


/**
 * @author cpatrick
 *
 */
@Translator(name="Midas", description="A translator for Midas")
public class MidasExecutionFactory extends ExecutionFactory<MidasConnectionFactory, MidasConnection>{

    public static final BundleUtil UTIL = BundleUtil.getBundleUtil(MidasExecutionFactory.class);

    public MidasExecutionFactory() {
        setSourceRequiredForMetadata(false);
    }

    @Override
    public void start() throws TranslatorException {
        super.start();
        LogManager.logTrace(LogConstants.CTX_CONNECTOR,
                "Midas ExecutionFactory Started"); //$NON-NLS-1$
    }

    public ResultSetExecution createResultSetExecution(QueryExpression command, ExecutionContext executionContext, RuntimeMetadata metadata, MidasConnection connection)
            throws TranslatorException {
        return new MidasQueryExecution((Select)command, connection, executionContext);
}

@Override
public boolean supportsCompareCriteriaEquals() {
return true;
}

@Override
public boolean supportsInCriteria() {
return true;
}

@Override
public boolean supportsLikeCriteria() {
return true;
}

@Override
public boolean supportsOrCriteria() {
return true;
}

@Override
public boolean supportsNotCriteria() {
return true;
}

@Override
public boolean supportsAggregatesCount() {
return true;
}

@Override
public boolean supportsAggregatesMax() {
return true;
}

@Override
public boolean supportsAggregatesMin() {
return true;
}

@Override
public boolean supportsAggregatesSum() {
return true;
}

@Override
public boolean supportsAggregatesAvg() {
return true;
}

@Override
public boolean supportsGroupBy() {
return true;
}

@Override
public boolean supportsOrderBy() {
return false;
}

@Override
public boolean supportsHaving() {
return false;
}

@Override
public boolean supportsCompareCriteriaOrdered() {
return true;
}

@Override
public boolean supportsRowLimit() {
return true;
}

@Override
public boolean supportsRowOffset() {
return true;
}

}
