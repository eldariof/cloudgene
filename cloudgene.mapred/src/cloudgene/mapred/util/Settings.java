package cloudgene.mapred.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;

public class Settings {

	private String hadoopPath = "";

	private String appsPath = "../cloudgene.tools";

	private String outputPath = "output";

	private String tempPath = "tmp";

	private String localWorkspace = "workspace";

	private String hdfsWorkspace = "cloudgene";

	private String streamingJar = "";

	private static Settings instance = null;

	private static final Log log = LogFactory.getLog(Settings.class);

	private boolean streaming = true;

	private Settings() {

	}

	public void load(String filename) throws FileNotFoundException,
			YamlException {

		YamlReader reader = new YamlReader(new FileReader(filename));

		instance = reader.read(Settings.class);

		// auto-search

		if (instance.streamingJar.isEmpty()
				|| !(new File(instance.streamingJar).exists())) {

			String version = HadoopUtil.getInstance().getVersion();
			String jar = "hadoop-streaming-" + version + ".jar";
			instance.streamingJar = FileUtil.path(instance.hadoopPath,
					"contrib", "streaming", jar);

			if (new File(instance.streamingJar).exists()) {

				log.info("Found streamingJar at " + instance.streamingJar + "");
				instance.streaming = true;
			} else {

				log.error("Streaming Jar could not be found automatically. Please specify it in config/settings.yaml. Streaming mode is disabled.");
				instance.streaming = false;
			}

		}

	}

	public static Settings getInstance() {
		if (instance == null) {
			instance = new Settings();
		}
		return instance;
	}

	public String getHadoopPath() {
		return hadoopPath;
	}

	public void setHadoopPath(String hadoopPath) {
		this.hadoopPath = hadoopPath;
	}

	public String getAppsPath() {
		return appsPath;
	}

	public void setAppsPath(String appsPath) {
		this.appsPath = appsPath;
	}

	public String getOutputPath() {
		return outputPath;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	public String getTempPath() {
		return tempPath;
	}

	public void setTempPath(String tempPath) {
		this.tempPath = tempPath;
	}

	public String getLocalWorkspace() {
		return localWorkspace;
	}

	public String getLocalWorkspace(String username) {
		return HdfsUtil.path(localWorkspace, username);
	}

	public void setLocalWorkspace(String localWorkspace) {
		this.localWorkspace = localWorkspace;
	}

	public String getHdfsWorkspace() {
		return hdfsWorkspace;
	}

	public String getHdfsWorkspace(String username) {
		return HdfsUtil.path(hdfsWorkspace, username);
	}

	public void setHdfsWorkspace(String hdfsWorkspace) {
		this.hdfsWorkspace = hdfsWorkspace;
	}

	public String getStreamingJar() {
		return streamingJar;
	}

	public void setStreamingJar(String streamingJar) {
		this.streamingJar = streamingJar;
	}

	public boolean isStreaming() {
		return streaming;
	}

	public void setStreaming(boolean streaming) {
		this.streaming = streaming;
	}

	public boolean testPaths() {

		/*
		 * if (!new File(appsPath).exists()) {
		 * 
		 * log.error("appsPath '" + appsPath + "' does not exist.");
		 * 
		 * return false;
		 * 
		 * }
		 * 
		 * String hadoop = FileUtil.path(hadoopPath, "hadoop");
		 * 
		 * if (!new File(hadoop).exists()) {
		 * 
		 * log.error("hadoop '" + hadoop + "' does not exist.");
		 * 
		 * return false;
		 * 
		 * }
		 * 
		 * if (!new File(streamingJar).exists()) {
		 * 
		 * log.error("streamingJar '" + streamingJar + "' does not exist.");
		 * 
		 * return false;
		 * 
		 * }
		 */

		return true;

	}

}
