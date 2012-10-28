package nu.nerd.SafeBuckets;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.PersistenceException;
import net.minecraft.server.Chunk;

import nu.nerd.SafeBuckets.database.SafeLiquid;
import nu.nerd.SafeBuckets.database.SafeLiquidTable;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.*;
import org.bukkit.craftbukkit.CraftChunk;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.block.CraftBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SafeBuckets extends JavaPlugin implements Listener {

    public class Bloop implements Block {

        String world;
        int x, y, z;
        public Bloop(String world, int x, int y, int z) {
            this.world = world;
            this.x = x;
            this.y = y;
            this.z = z;
        }
        @Override
        public byte getData() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Block getRelative(int i, int i1, int i2) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Block getRelative(BlockFace bf) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Block getRelative(BlockFace bf, int i) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Material getType() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public int getTypeId() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public byte getLightLevel() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public byte getLightFromSky() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public byte getLightFromBlocks() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public World getWorld() {
            return getServer().getWorld(this.world);
        }

        @Override
        public int getX() {
            return x;
        }

        @Override
        public int getY() {
            return y;
        }

        @Override
        public int getZ() {
            return z;
        }

        @Override
        public Location getLocation() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public org.bukkit.Chunk getChunk() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void setData(byte b) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void setData(byte b, boolean bln) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void setType(Material mtrl) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean setTypeId(int i) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean setTypeId(int i, boolean bln) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean setTypeIdAndData(int i, byte b, boolean bln) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public BlockFace getFace(Block block) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public BlockState getState() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Biome getBiome() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean isBlockPowered() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean isBlockIndirectlyPowered() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean isBlockFacePowered(BlockFace bf) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean isBlockFaceIndirectlyPowered(BlockFace bf) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public int getBlockPower(BlockFace bf) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public int getBlockPower() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean isEmpty() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean isLiquid() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public double getTemperature() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public double getHumidity() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public PistonMoveReaction getPistonMoveReaction() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean breakNaturally() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean breakNaturally(ItemStack is) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Collection<ItemStack> getDrops() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Collection<ItemStack> getDrops(ItemStack is) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void setMetadata(String string, MetadataValue mv) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public List<MetadataValue> getMetadata(String string) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean hasMetadata(String string) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void removeMetadata(String string, Plugin plugin) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void setBiome(Biome biome) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
    
    private final SafeBucketsListener l = new SafeBucketsListener(this);
    public SafeLiquidTable table;
    public static final Logger log = Logger.getLogger("Minecraft");
    public HashMap<String, HashSet<Long>> cachedSafeBlocks = new HashMap<String, HashSet<Long>>();
    //public HashSet<Long> cachedSafeBlocks = new HashSet<Long>();

    @Override
    public void onDisable() {
        log.log(Level.INFO, "[" + getDescription().getName() + "] " + getDescription().getVersion() + " disabled.");
    }

    @Override
    public void onEnable() {
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(l, this);

        setupDatabase();
        table = new SafeLiquidTable(this);

        getServer().getPluginManager().registerEvents(this, this);
        
        log.log(Level.INFO, "[" + getDescription().getName() + "] " + getDescription().getVersion() + " enabled.");
    }
    
    @EventHandler
    public void onCommandPreProcess(PlayerCommandPreprocessEvent event) {
        if (event.getMessage().equalsIgnoreCase("/blah")) {
            List<SafeLiquid> liquids = getDatabase().find(SafeLiquid.class).findList();
            
            long start = System.currentTimeMillis();
            long end = System.currentTimeMillis();
            // Do new test
            for (int i = 0; i < 1000; i++){
                for (SafeLiquid liq : liquids) {
                    this.isSafeLiquid(new Bloop(liq.getWorld(), liq.getX(), liq.getY(), liq.getZ()));
                }
            }
            
            end = System.currentTimeMillis();
            
            getLogger().log(Level.INFO, "Total Time New: " + (end - start));
            
            start = System.currentTimeMillis();
            // Do old test
            for (int i = 0; i < 1000; i++){
                for (SafeLiquid liq : liquids) {
                    table.isSafeLiquid(new Bloop(liq.getWorld(), liq.getX(), liq.getY(), liq.getZ()));
                }
            }
            
            end = System.currentTimeMillis();
            
            getLogger().log(Level.INFO, "Total Time Old: " + (end - start));
        }
    }

    public boolean setupDatabase() {
        try {
            getDatabase().find(SafeLiquid.class).findRowCount();
            List<SafeLiquid> liquids = getDatabase().find(SafeLiquid.class).findList();
            for (SafeLiquid l : liquids) {
                addSafeLiquidToCache(l);
            }
            getLogger().log(Level.INFO, "Total in db: " + liquids.size());
            int blah = 0;
            for (String w : cachedSafeBlocks.keySet()) {
                blah += cachedSafeBlocks.get(w).size();
            }
            getLogger().log(Level.INFO, "Total in cache: " + blah);
        } catch (PersistenceException ex) {
            getLogger().log(Level.INFO, "First run, initializing database.");
            installDDL();
            return true;
        }

        return false;
    }

    public void addBlockToCacheAndDB(Block block) {
        SafeLiquid stat = new SafeLiquid();
        stat.setWorld(block.getWorld().getName());
        stat.setX(block.getX());
        stat.setY(block.getY());
        stat.setZ(block.getZ());
        table.save(stat);
        
        addSafeLiquidToCache(stat);
    }
    
    public void removeSafeLiquidFromCacheAndDB(Block block) {
        String world = block.getWorld().getName();
        Long l = Util.GetHashCode(block.getX(), block.getY(), block.getZ());
        if (cachedSafeBlocks.containsKey(world)) {
            cachedSafeBlocks.get(world).remove(l);
        }
        
        table.removeSafeLiquid(block);
    }
    
    public boolean isSafeLiquid(Block block) {
        String world = block.getWorld().getName();
        Long l = Util.GetHashCode(block.getX(), block.getY(), block.getZ());
        
        if (cachedSafeBlocks.containsKey(world)) {
            return cachedSafeBlocks.get(world).contains(l);
        }
        return false;
    }

    public void addSafeLiquidToCache(SafeLiquid liquid) {
        String world = liquid.getWorld();
        if (!cachedSafeBlocks.containsKey(world)) {
            cachedSafeBlocks.put(world, new HashSet<Long>());
        }
        cachedSafeBlocks.get(world).add(Util.GetHashCode(liquid.getX(), liquid.getY(), liquid.getZ()));
        getLogger().log(Level.INFO, "Added SafeLiquid to cache: " + liquid.getWorld() + "[" + liquid.getX() + "," + liquid.getY() + "," + liquid.getZ() + "]");
    }

    @Override
    public ArrayList<Class<?>> getDatabaseClasses() {
        ArrayList<Class<?>> list = new ArrayList<Class<?>>();
        list.add(SafeLiquid.class);
        return list;
    }
}
