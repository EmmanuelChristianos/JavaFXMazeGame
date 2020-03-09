package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import unsw.entity.*;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image wallImageTopMiddle;
    private Image wallImageLeftMiddle;
    private Image wallImageRightMiddle;
    private Image wallImageDowntMiddle;
    private Image wallImageTopLeft;
    private Image wallImageTopRight;
    private Image wallImageBottomLeft;
    private Image wallImageBottomRight;
    private Image wallImageMiddle;
    private Image boulderImage;
    private Image floorSwitchImage;
    private Image doorImage;
    private Image enemyImage;
    private Image exitImage;
    private Image invinciblePotImage;
    private Image keyImage;
    private Image portalImage;
    private Image swordImage;
    private Image treasureImage;
    private Image portalGunImage;
    private Image fuelImage;

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image("/pickle2.png");
        wallImageTopMiddle = new Image("/topMiddle.png");
        wallImageLeftMiddle = new Image("/leftMiddle.png");
        wallImageRightMiddle = new Image("/rightMiddle.png");
        wallImageDowntMiddle = new Image("/bottomMiddle.png");
        wallImageTopLeft = new Image("/topLeft.png");
        wallImageMiddle = new Image("/flowers.png");
        wallImageTopRight = new Image("/topRight.png");
        wallImageBottomLeft = new Image("/bottomLeft.png");
        wallImageBottomRight = new Image("/bottomRight.png");
        boulderImage = new Image("/boulder.png");
        floorSwitchImage = new Image("/pressure_plate.png");
        doorImage = new Image("/closed_door.png");
        enemyImage = new Image("/enemy.png");
        exitImage = new Image("/exit.png");
        invinciblePotImage = new Image("/brilliant_blue_new.png");
        keyImage = new Image("/key.png");
        portalImage = new Image("/portal.png");
        swordImage = new Image("/greatsword_1_new.png");
        treasureImage = new Image("/gold_pile.png");
        portalGunImage = new Image("/portalGun.png");
        fuelImage = new Image("/fuel.png");
    }

    @Override
    public void onLoad(Entity player) {
        ImageView view = new ImageView(playerImage);
        view.setId("1");
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
    	
    	if(wall.getX() == 0 && wall.getY() == 0) {
    		
    		//TopLeftCorner
    		ImageView view = new ImageView(wallImageTopLeft);
    		addEntity(wall, view);
    	} else if (wall.getX() == wall.getDungeon().getWidth()-1 && wall.getY() == 0) {
    		
    		//TopRightCorner
    		ImageView view = new ImageView(wallImageTopRight);
    		addEntity(wall, view);
    	} else if (wall.getX() == 0 && wall.getY() == wall.getDungeon().getHeight()-1) {
    		
    		//BottomLeftCorner
    		ImageView view = new ImageView(wallImageBottomLeft);
    		addEntity(wall, view);
    	} else if (wall.getX() == wall.getDungeon().getWidth()-1 && wall.getY() == wall.getDungeon().getHeight()-1) {
    		
    		//BottomRightCorner
    		ImageView view = new ImageView(wallImageBottomRight);
    		addEntity(wall, view);
    	} else if (wall.getX() == 0) {
    		
    		//LeftMiddle
    		ImageView view = new ImageView(wallImageLeftMiddle);
    		addEntity(wall, view);
    	} else if (wall.getY() == 0) {
    		
    		//TopMiddle
    		ImageView view = new ImageView(wallImageTopMiddle);
    		addEntity(wall, view);
    	} else if (wall.getY() == wall.getDungeon().getHeight()-1) {
    		
    		//BottomMiddle
    		ImageView view = new ImageView(wallImageDowntMiddle);
    		addEntity(wall, view);
    	} else if (wall.getX() == wall.getDungeon().getWidth()-1) {
    		
    		//RightMiddle
    		ImageView view = new ImageView(wallImageRightMiddle);
    		addEntity(wall, view);
    	} else {
    		ImageView view = new ImageView(wallImageMiddle);
    		addEntity(wall, view);
    	}
    }
    
    @Override
    public void onLoad(Fuel fuel) {
    	ImageView view = new ImageView(fuelImage);
        addEntity(fuel, view);
    }
    
    @Override
    public void onLoad(Portalgun portalGun) {
    	ImageView view = new ImageView(portalGunImage);
        addEntity(portalGun, view);
    }
    
    @Override
    public void onLoad(Boulder boulder) {
    	ImageView view = new ImageView(boulderImage);
        addEntity(boulder, view);
    }
    
    @Override
    public void onLoad(FloorSwitch floorSwitch) {
    	ImageView view = new ImageView(floorSwitchImage);
        addEntity(floorSwitch, view);
    }
    
    @Override
    public void onLoad(Door door) {
    	ImageView view = new ImageView(doorImage);
        addEntity(door, view);
    }
    
    @Override
    public void onLoad(Enemy enemy) {
    	ImageView view = new ImageView(enemyImage);
        addEntity(enemy, view);
    }
    
    @Override
    public void onLoad(Exit exit) {
    	ImageView view = new ImageView(exitImage);
        addEntity(exit, view);
    }
    
    @Override
    public void onLoad(InvincibilityPotion invinciblePot) {
    	ImageView view = new ImageView(invinciblePotImage);
        addEntity(invinciblePot, view);
    }
    
    @Override
    public void onLoad(Key key) {
    	ImageView view = new ImageView(keyImage);
        addEntity(key, view);
    }
    
    @Override
    public void onLoad(Portal portal) {
    	ImageView view = new ImageView(portalImage);
        addEntity(portal, view);
    }
    
    @Override
    public void onLoad(Sword sword) {
    	ImageView view = new ImageView(swordImage);
        addEntity(sword, view);
    }
    
    @Override
    public void onLoad(Treasure treasure) {
    	ImageView view = new ImageView(treasureImage);
        addEntity(treasure, view);
    }

    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if(newValue.intValue() < 0) {
            		
            		newValue = 0;
            	}
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
            	if(newValue.intValue() < 0) {
            		
            		newValue = 0;
            	}
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
        entity.visible().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if (newValue.intValue() == 0) {
					node.setVisible(false);
				}
                else if (newValue.intValue() == 1) {
					node.setVisible(true);
					node.toFront();
				}
            }
        });
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }


}
