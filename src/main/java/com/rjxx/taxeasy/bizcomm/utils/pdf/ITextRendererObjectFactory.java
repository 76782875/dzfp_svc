package com.rjxx.taxeasy.bizcomm.utils.pdf;

import com.itextpdf.text.pdf.BaseFont;
import com.rjxx.utils.ResourceLoader;
import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;

/**
 * ITextRenderer对象工厂,提供性能,加载中文字体集(大小20M),增加对象池
 * Created by Him on 2015-09-22.
 */
public class ITextRendererObjectFactory extends BasePoolableObjectFactory {
    private static GenericObjectPool itextRendererObjectPool = null;

    @Override
    public Object makeObject() throws Exception {
        ITextRenderer renderer = createTextRenderer();
        return renderer;
    }

    /**
     * 获取对象池,使用对象工厂 后提供性能,能够支持 500线程 迭代10
     *
     * @return GenericObjectPool
     * @Title: getObjectPool
     * @Description: 获取对象池
     * @author
     */
    public static GenericObjectPool getObjectPool() {
        synchronized (ITextRendererObjectFactory.class) {
            if (itextRendererObjectPool == null) {
                itextRendererObjectPool = new GenericObjectPool(
                        new ITextRendererObjectFactory());
                GenericObjectPool.Config config = new GenericObjectPool.Config();
                //config.lifo = false;
                config.maxActive = 15;
                config.maxIdle = 5;
                config.minIdle = 1;
                config.maxWait = 5 * 1000;
                itextRendererObjectPool.setConfig(config);
            }
        }
        return itextRendererObjectPool;
    }

    /**
     * 初始化
     *
     * @return
     * @throws Exception
     * @Title: initTextRenderer
     * @Description:
     * @author
     */
    public static synchronized ITextRenderer createTextRenderer() throws Exception {
        ITextRenderer renderer = new ITextRenderer();
        ITextFontResolver fontResolver = renderer.getFontResolver();
        addFonts(fontResolver);
        return renderer;
    }

    /**
     * 添加字体
     *
     * @param fontResolver
     * @throws Exception
     * @Title: addFonts
     * @Description:
     * @author lihengjun 修改时间： 2013年11月5日 下午1:37:57 修改内容：新建
     */
    public static ITextFontResolver addFonts(ITextFontResolver fontResolver)
            throws Exception {
        // Font fontChinese = null;
        // BaseFont bfChinese = BaseFont.createFont("STSong-Light",
        // "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        // fontChinese = new Font(bfChinese, 12, Font.NORMAL);

        File fontsDir = new File(ResourceLoader.getPath("config/fonts"));
        if (fontsDir != null && fontsDir.isDirectory()) {
            File[] files = fontsDir.listFiles();
            for (int i = 0; i < files.length; i++) {
                File f = files[i];
                if (f == null || f.isDirectory()) {
                    break;
                }
                fontResolver.addFont(f.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            }
        }
        return fontResolver;
    }
}

