package janellope.digicraft.worldgen;

import java.util.Random;

import janellope.digicraft.block.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModWorldGen implements IWorldGenerator{

	private WorldGenerator oreCopper;
	
	public static void init()
	{
		GameRegistry.registerWorldGenerator(new ModWorldGen(), 0);
	}
	
	public ModWorldGen()
	{
		this.oreCopper = new ModWorldGenMinable(ModBlocks.oreCopper.getDefaultState(),3);
		
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider)
	{
		
		switch(world.provider.getDimension())
		{
		case 0:  //Overworld
			
			this.runGenerator(this.oreCopper, world, random, chunkX, chunkZ, 30, 20, 55);
			
			break;
			
		case -1:  //Nether
			
			break;
			
		case 1:  //End
			
			break;
			
		}
	}
	
	private void runGenerator(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chanceToSpawn, int minHeight, int maxHeight)
	{
		if (minHeight< 0 || maxHeight > 256 || minHeight >maxHeight)
			throw new IllegalArgumentException("Minimum or maximum height out of bounds");
		int heightDiff = maxHeight - minHeight +1;
		for (int i = 0; i < chanceToSpawn; i++)
		{
			int x = chunkX * 16 + random.nextInt(16);
			int y = minHeight + random.nextInt(heightDiff);
			int z = chunkZ * 16 + random.nextInt(16);
			generator.generate(world, random, new BlockPos(x,y,z));
			
		}
				
	}
	
}
