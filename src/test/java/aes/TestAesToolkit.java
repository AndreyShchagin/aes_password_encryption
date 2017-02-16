package aes;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.poc.security.ASecurityToolkit;
import com.poc.security.AesToolkit;

/**
 * @author Andrey Shchagin on 11/23/15.
 */
public class TestAesToolkit {
    private Logger LOG = LoggerFactory.getLogger(TestAesToolkit.class);
    @Test
    public void testEncryption(){
        ASecurityToolkit sec = new AesToolkit();
        LOG.debug(sec.encrypt("testAllPagos"));
        Assert.assertEquals("ￋ\uFFFFzweﾎￎrE\u000Fa\uFFF99Kￂﾉ", sec.encrypt("testSayUsername"));
    }

    @Test
    public void testDecryption(){
        ASecurityToolkit sec = new AesToolkit();
        Assert.assertEquals("testSayUsername", sec.decrypt("ￋ\uFFFFzweﾎￎrE\u000Fa\uFFF99Kￂﾉ"));
    }
}
