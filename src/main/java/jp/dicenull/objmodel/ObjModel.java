package jp.dicenull.objmodel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@Mod(
        modid = ObjModel.MOD_ID,
        name = ObjModel.MOD_NAME,
        version = ObjModel.VERSION
)
public class ObjModel {

    public static final String MOD_ID = "obj-model";
    public static final String MOD_NAME = "obj-model";
    public static final String VERSION = "2019.3-1.3.2";

    /**
     * This is the instance of your mod as created by Forge. It will never be null.
     */
    @Mod.Instance(MOD_ID)
    public static ObjModel INSTANCE;

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {

    }

    /**
     * This is the second initialization event. Register custom recipes
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    /**
     * This is the final initialization event. Register actions from other mods here
     */
    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {

    }

    /**
     * Forge will automatically look up and bind blocks to the fields in this class
     * based on their registry name.
     */
    @GameRegistry.ObjectHolder(MOD_ID)
    public static class Blocks {
      /*
          public static final MySpecialBlock mySpecialBlock = null; // placeholder for special block below
      */
      public static final Block bars = null;
    }

    /**
     * Forge will automatically look up and bind items to the fields in this class
     * based on their registry name.
     */
    @GameRegistry.ObjectHolder(MOD_ID)
    public static class Items {
      public static final Item dice = null;
      public static final Item bars = null;
    }

    /**
     * This is a special class that listens to registry events, to allow creation of mod blocks and items at the proper time.
     */
    @Mod.EventBusSubscriber
    public static class ObjectRegistryHandler {
        /**
         * Listen for the register event for creating custom items
         */
        @SubscribeEvent
        public static void addItems(RegistryEvent.Register<Item> event) {
            event.getRegistry().register(new Dice()
                    .setRegistryName(MOD_ID, "dice"));


            event.getRegistry().registerAll(
                    new ItemBlock(Blocks.bars).setRegistryName("bars")
            );
           /*
             event.getRegistry().register(new ItemBlock(Blocks.myBlock).setRegistryName(MOD_ID, "myBlock"));
             event.getRegistry().register(new MySpecialItem().setRegistryName(MOD_ID, "mySpecialItem"));
            */

        }

        /**
         * Listen for the register event for creating custom blocks
         */
        @SubscribeEvent
        public static void addBlocks(RegistryEvent.Register<Block> event) {

            event.getRegistry().registerAll(
                    new Bars()
                            .setRegistryName(MOD_ID, "bars")
                            .setTranslationKey("bars")
                            .setHardness(1.5F)
                            .setResistance(1.0F));
        }

        @SubscribeEvent
        public static void registerModels(ModelRegistryEvent event) throws Exception {
            OBJLoader.INSTANCE.addDomain(MOD_ID);

            ModelLoader.setCustomModelResourceLocation(Items.dice, 0, new ModelResourceLocation(Items.dice.getRegistryName(), "inventory"));
            ModelLoader.setCustomModelResourceLocation(Items.bars, 0, new ModelResourceLocation(Items.bars.getRegistryName(), "inventory"));
        }
    }
    /* EXAMPLE ITEM AND BLOCK - you probably want these in separate files
    public static class MySpecialItem extends Item {

    }

    public static class MySpecialBlock extends Block {

    }
    */

    public static class Dice extends Item {
        protected Dice() {
            super();
            setTranslationKey("dice");
            setCreativeTab(ObjModelTab.ModTab);
        }
    }

    public static class Bars extends Block {

        protected static final AxisAlignedBB DEFAULT_AABB = new AxisAlignedBB(0.05D, 0D, 0.05D, 0.94D, 0.46D, 0.94D);

        protected Bars() {
            super(Material.GLASS);
            this.setCreativeTab(ObjModelTab.ModTab);
            this.setLightOpacity(0);
        }

        public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
            return DEFAULT_AABB;
        }

        @Nullable
        public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
            return DEFAULT_AABB;
        }

        public boolean isOpaqueCube(IBlockState state)
        {
            return false;
        }

        public boolean isFullCube() {
            return false;
        }

        @Override
        public float getAmbientOcclusionLightValue(IBlockState state) {
            return 1.0F;
        }
    }
}
