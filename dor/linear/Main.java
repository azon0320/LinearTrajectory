package dor.linear;

/**
  * Linear Trajectory Source Code
  * @author Dormice
  */
   

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.ProjectileLaunchEvent;
import cn.nukkit.event.EventListener;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.projectile.*;

public final class Main extends PluginBase implements Listener{
  public static Main instance;
  
  private Listener l;
  
  
  class Linear extends EntitySnoowball{
    public Linear(FullChunk f, CompoundTag c){
     this(f, c, null);
    }
    
    public Linear(FullChunk f, CompoundTag c, Entity s){
      super(f,c,s);
    }
    
    protected float getGravity(){
      return 0.00f;
    }
  }
  
  public static Main getInstance() throws NullPointerException{
    if(instance == null){
      throw new NullPointerException();
    }else{
      return instance;
    }
  }
  
  public Main(){}
  
  @Override
  public void onEnable(){
    getServer().getPluginManager().registerEvents(this, this);
    getLogger().info("Linear Plugin Enabled!");
    Main.instance  =this;
  }

  @Override
  public void onDisable(){}
  
  @EventListener
  public void onProjectileLaunchEvent(ProjectileLaunchEvent e){
    EntityProjectile ep = e.getEntity();
    if(e.getNetworkId() != 81) return;
    EntitySnowball es = (EntitySnowball) ep;
    ep = null;
    e.setCancelled();
    (new Linear(es.chunk, es.namedtag, es.shootingEntity)).spawnToAll();
    es = null;e = null;
  }
}
