package org.tievoli.framework.configuration;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

import org.apache.commons.configuration.ConfigurationUtils;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

/**
 * 文件刷新策略.
 * 
 * @author Ryan Jiang
 * 
 */
public class FileChangedReloadingStrategyExtention extends FileChangedReloadingStrategy {
	static final String PROTOCOL_FILE = "file";

	protected File getFile() {
		return ((this.configuration.getURL() != null) ? fileFromURL(this.configuration.getURL()) : this.configuration.getFile());
	}

	private File fileFromURL(URL url) {
		if ("jar".equals(url.getProtocol())) {
			String path = url.getPath();
			try {
				return ConfigurationUtils.fileFromURL(new URL(path.substring(0, path.indexOf(33))));
			} catch (MalformedURLException mex) {
				return null;
			}

		}

		return getFileName(url);
	}

	private File getFileName(URL url) {
		if ((url == null) || (!(url.getProtocol().equals("file")))) {
			return null;
		}

		String filename;
		try {
			filename = URLDecoder.decode(url.getFile(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("url is not decode:" + url, e);
		}
		return new File(filename);
	}
}
