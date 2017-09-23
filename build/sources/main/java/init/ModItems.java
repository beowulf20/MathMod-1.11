package init;

import com.joarez.mathmod.MathMod;
import com.joarez.mathmod.items.ItemTutorial;
import com.joarez.mathmod.items.Names;

import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {
	
	public static ItemTutorial tutorialItem;
	
	public static void init() {
		tutorialItem = new ItemTutorial();
		tutorialItem.setRegistryName(new ResourceLocation(MathMod.MOD_ID,Names.TUTORIAL_ITEM));
		
		GameRegistry.register(tutorialItem);
	}
	
	public static void initRecipes() {
		tutorialItem.addRecipes();
	}
	
	@SideOnly(Side.CLIENT)
	public static void initClient(ItemModelMesher mesher) {
		
		ModelResourceLocation model = new ModelResourceLocation(MathMod.RESOURCE_PREFIX+Names.TUTORIAL_ITEM, "inventory");
		ModelLoader.registerItemVariants(tutorialItem, model);
		mesher.register(tutorialItem, 0, model);
		
	}
}
