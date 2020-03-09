package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import unsw.entity.Entity;
import unsw.entity.InvincibilityPotion;
import unsw.entity.Key;
import unsw.entity.Player;
import unsw.entity.Sword;
import unsw.entity.Treasure;
import unsw.goal.GoalComponant;
import unsw.goal.GoalLeaf;
import unsw.goal.LevelGoal;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;
    
    @FXML
    private VBox goals;
    
    @FXML
    private Label hasKey;
    
    @FXML
    private Label hasPotion;
    
    @FXML
    private Label hasSword;
    
    @FXML
    private Label numGold;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;
    
    private StartScreen startScreen;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
    }

    @FXML
    public void initialize() {
        Image ground = new Image("/grass.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        ImageView eny = null; 
        for (ImageView entity : initialEntities) {
        	
        	if(entity.getId() == null) {
        		
        		squares.getChildren().add(entity);
        		
        	} else {
        		eny = entity;
        	}
        }
        squares.getChildren().add(eny);
        
        showGoals(dungeon.getGoals());   
        
        for (Entity en: dungeon.getEntities()) {
        	if (en instanceof Key) {
        		hasKey.textProperty().bind(((Key) en).booleanProperty().asString());
        		((Key) en).booleanProperty().addListener(new ChangeListener<Boolean>() {
        			@Override
        			public void changed(ObservableValue<? extends Boolean> observable,
        					Boolean oldVal, Boolean newVal) {
        				if (newVal == true)
        					System.out.println("Key has been picked up");
        				if (newVal == false)
        					System.out.println("Key has been dropped");
        			}
        		});
        	} else if (en instanceof InvincibilityPotion) { 
        		((InvincibilityPotion) en).booleanProperty().addListener(new ChangeListener<Boolean>() {
        			@Override
        			public void changed(ObservableValue<? extends Boolean> observable,
        					Boolean oldVal, Boolean newVal) {
        				if (newVal == true) {
        					System.out.println("Pot has been picked up");        					
        					hasPotion.textProperty().set(Integer.toString(((InvincibilityPotion) en).getTimer()));
        					((InvincibilityPotion) en).timerProperty().addListener(new ChangeListener<Number>() {
        						@Override
        						public void changed(ObservableValue<? extends Number> observable,
        								Number oldVal, Number newVal) {
        							if (newVal.intValue() == -1) {        								
        	        					hasPotion.textProperty().set("false");
        							} else {
        								hasPotion.textProperty().set(Integer.toString(((InvincibilityPotion) en).getTimer()));
        							}
        							
        						}
        					});
        				} else {
        					System.out.println("Pot has been dropped");
        				}
        			}
				});
        	} else if (en instanceof Sword) {
        		((Sword) en).booleanProperty().addListener(new ChangeListener<Boolean>() {
        			@Override
        			public void changed(ObservableValue<? extends Boolean> observable,
        					Boolean oldVal, Boolean newVal) {
        				if (newVal == true) {
        					System.out.println("Sword has been picked up");        					
        					 hasSword.textProperty().set(Integer.toString(((Sword) en).getHits()));
        					((Sword) en).hitsProperty().addListener(new ChangeListener<Number>() {
        						@Override
        						public void changed(ObservableValue<? extends Number> observable,
        								Number oldVal, Number newVal) {
        							if (newVal.intValue() == -1) {        								
        	        					hasSword.textProperty().set("false");
        							} else {
        								hasSword.textProperty().set(Integer.toString(((Sword) en).getHits()));
        							}
        							
        						}
        					});
        				} else {
        					System.out.println("Sword has been dropped");
        					hasSword.textProperty().set("false");
        				}
        			}
				});
        	} else if (en instanceof Treasure) {
        		((Treasure) en).pickedProperty().addListener(new ChangeListener<Boolean>() {
        			@Override
        			public void changed(ObservableValue<? extends Boolean> observable,
        					Boolean oldVal, Boolean newVal) {
    					System.out.println("Gold has been picked up");
    					numGold.textProperty().set(Integer.toString(Integer.parseInt(numGold.getText())+1));
        			}
        		});
        	} else if (en instanceof Player) {
        		((Player) en).booleanProperty().addListener(new ChangeListener<Boolean>() {
        			@Override
        			public void changed(ObservableValue<? extends Boolean> observable,
        					Boolean oldVal, Boolean newVal) {
    					System.out.println("Return to start menu");
    					startScreen.start();
        			}
        		});
        	}
        }
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            player.moveUp();
            break;
        case DOWN:
            player.moveDown();
            break;
        case LEFT:
            player.moveLeft();
            break;
        case RIGHT:
            player.moveRight();
            break;
        case ESCAPE:
        	startScreen.start();
        default:
            break;
        }
    }
    
    public void setStartScreen(StartScreen startScreen) {
    	this.startScreen = startScreen;
    }
    
    public List<ImageView> getImages() {
    	return this.initialEntities;
    }
    
    private void showGoals(GoalComponant goal) {
    		
    	if (goal instanceof LevelGoal) {
    		for (GoalComponant goals: ((LevelGoal) goal).getGoals()) {
    			showGoals(goals);
    		}
    	} else if (goal instanceof GoalLeaf) {
    		Label label = new Label(((GoalLeaf) goal).toString());
    		label.setTextFill(Color.web("red"));
    		
    		((GoalLeaf) goal).booleanProperty().addListener(new ChangeListener<Boolean>() {
    			@Override
    			public void changed(ObservableValue<? extends Boolean> observable,
    					Boolean oldVal, Boolean newVal) {
    				if (newVal.booleanValue() == true) {
    					label.setTextFill(Color.web("green"));
    					label.setText(((GoalLeaf) goal).toString());
    				} else {
    					label.setTextFill(Color.web("red"));
    					label.setText(((GoalLeaf) goal).toString());
    				}
    			}
    		});
    		
    		goals.getChildren().add(label);

		}
    }
}

