package dodd.cotro;

import net.minecraftforge.fml.common.Mod;

@Mod(modid = Global.MOD_ID, name = Global.MOD_NAME, version = Global.VERSION, dependencies = Global.DEPENDENCIES)
public class CoTRO {
	
	/*
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		if (Loader.isModLoaded("base")) {
			orderRegistry(Base.instance);
		}
		if (Loader.isModLoaded("contenttweaker")) {
			orderRegistry(ContentTweaker.instance);
		}
	}
	
	private void orderRegistry(BaseModFoundation instance) {
		try {
			Map<String, Registry> registries = instance.getAllRegistries();
			for (Registry registry : registries.values()) {
				FieldUtils.writeField(registry, "entries", new Object2ObjectAVLTreeMap<ResourceLocation, Object>(registry.getEntries()), true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
}
