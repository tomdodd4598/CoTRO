package dodd.cotro.core;

import java.util.Map;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.Name("CoTROCore")
@IFMLLoadingPlugin.MCVersion("1.12.2")
@IFMLLoadingPlugin.TransformerExclusions("dodd.cotro.core")
@IFMLLoadingPlugin.SortingIndex(4598)
public class Plugin implements IFMLLoadingPlugin {
	
	@Override
	public String[] getASMTransformerClass() {
		return new String[] {"dodd.cotro.core.Transformer"};
	}
	
	@Override
	public String getModContainerClass() {
		return null;
	}
	
	@Override
	public String getSetupClass() {
		return null;
	}
	
	@Override
	public void injectData(Map<String, Object> data) {
		
	}
	
	@Override
	public String getAccessTransformerClass() {
		return null;
	}
}
