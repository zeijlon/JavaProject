package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreas Zeijlon on 2015-03-21.
 */
public class EnemySpawner {
    public GRID grid;

    public List<Enemy> enemies = new ArrayList<>();
    public int spawnRate = 300, spawnTime = 0;


    Image basicEnemy = Toolkit.getDefaultToolkit().getImage("images/basicEnemy.png");

    public EnemySpawner(GRID grid) {
        this.grid = grid;
    }

    public void spawnBasicEnemy() {
        if (spawnTime >= spawnRate) {
            Enemy basic = new BasicEnemy();
            enemies.add(basic);
            for (int y = 0; y < grid.getSquares().length; y++) {
                for (int x = 0; x < grid.getSquares()[y].length; x++) {
                    if (grid.getSquares()[y][x] == 3) {
                        basic.yC = y;
                        basic.xC = x;
                        basic.Y = y * 40;
                        basic.X = x * 40;
                    }
                }
            }
            basic.enemySpawned = true;
            spawnTime = 0;
        } else {
            spawnTime++;
        }
    }

    public void moveEnemy() {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).direction == Enemy.right) {
                enemies.get(i).X += enemies.get(i).getSpeed();
            } else if (enemies.get(i).direction == Enemy.left) {
                enemies.get(i).X -= enemies.get(i).getSpeed();
            } else if (enemies.get(i).direction == Enemy.down) {
                enemies.get(i).Y += enemies.get(i).getSpeed();
            } else if (enemies.get(i).direction == Enemy.up) {
                enemies.get(i).Y -= enemies.get(i).getSpeed();
            }

            enemies.get(i).enemyWalk += enemies.get(i).getSpeed();
            System.out.println(enemies.get(i).enemyWalk);


            if (enemies.get(i).enemyWalk == GameComponent.TILE_SIZE) {

                if (enemies.get(i).direction == Enemy.right) {
                    enemies.get(i).xC += 1;
                    enemies.get(i).hasRight = true;
                }
                else if (enemies.get(i).direction == Enemy.left) {
                    enemies.get(i).hasLeft = true;
                    enemies.get(i).xC -= 1;
                } else if (enemies.get(i).direction == Enemy.down) {
                    enemies.get(i).yC += 1;
                    enemies.get(i).hasDown = true;
                } else if (enemies.get(i).direction == Enemy.up) {
                    enemies.get(i).yC -= 1;
                    enemies.get(i).hasUp = true;
                }
                if (!enemies.get(i).hasUp) {
                    if (grid.getSquares()[enemies.get(i).yC-1][enemies.get(i).xC] == GRID.PATH || grid.getSquares()[enemies.get(i).yC-1][enemies.get(i).xC] == GRID.FINISH) {
                        enemies.get(i).direction = Enemy.up;
                    }

                } else if (!enemies.get(i).hasDown) {
                    if (grid.getSquares()[enemies.get(i).yC+1][enemies.get(i).xC] == GRID.PATH || grid.getSquares()[enemies.get(i).yC+1][enemies.get(i).xC] == GRID.FINISH) {
                        enemies.get(i).direction = Enemy.down;
                    }

                } else if (!enemies.get(i).hasLeft) {
                    if (grid.getSquares()[enemies.get(i).yC][enemies.get(i).xC-1] == GRID.PATH || grid.getSquares()[enemies.get(i).yC][enemies.get(i).xC-1] == GRID.FINISH) {
                        enemies.get(i).direction = Enemy.left;
                    }

                } else if (!enemies.get(i).hasRight) {
                    if (grid.getSquares()[enemies.get(i).yC][enemies.get(i).xC+1] == GRID.PATH || grid.getSquares()[enemies.get(i).yC][enemies.get(i).xC+1] == GRID.FINISH) {
                        enemies.get(i).direction = Enemy.right;
                    }

                }
                enemies.get(i).hasRight = false;
                enemies.get(i).hasLeft = false;
                enemies.get(i).hasDown = false;
                enemies.get(i).hasUp = false;
                enemies.get(i).enemyWalk = 0;

            }
        }
    }

           /* if (enemies.get(i).direction == Enemy.right) {
                 if (grid.getSquares()[y / 40][(x + 40) / 40] == GRID.PATH || grid.getSquares()[y / 40][(x + 40) / 40] == GRID.FINISH) {
                    enemies.get(i).X += enemies.get(i).getSpeed();
                }
                else if (grid.getSquares()[(y - 40) / 40][x / 40] == GRID.PATH || grid.getSquares()[(y - 40) / 40][x / 40] == GRID.FINISH) {
                    enemies.get(i).direction = Enemy.up;
                    System.out.println("up");
                } else if (grid.getSquares()[(y + 40) / 40][x / 40] == GRID.PATH || grid.getSquares()[(y + 40) / 40][x / 40] == GRID.FINISH) {
                    enemies.get(i).direction = Enemy.down;
                    System.out.println("down");
                }
            } else if (enemies.get(i).direction == Enemy.left) {
                 if (grid.getSquares()[y / 40][(x - 40) / 40] == GRID.PATH || grid.getSquares()[y / 40][(x - 40) / 40] == GRID.FINISH) {
                    enemies.get(i).X -= enemies.get(i).getSpeed();
                }
                else if (grid.getSquares()[(y - 40) / 40][x / 40] == GRID.PATH || grid.getSquares()[(y - 40) / 40][x / 40] == GRID.FINISH) {
                    enemies.get(i).direction = Enemy.up;
                } else if (grid.getSquares()[(y + 40) / 40][x / 40] == GRID.PATH || grid.getSquares()[(y + 40) / 40][x / 40] == GRID.FINISH) {
                    enemies.get(i).direction = Enemy.down;
                }
            } else if (enemies.get(i).direction == Enemy.up) {
                 if (grid.getSquares()[(y - 40) / 40][x / 40] == GRID.PATH || grid.getSquares()[(y - 40) / 40][(x) / 40] == GRID.FINISH) {
                    enemies.get(i).Y -= enemies.get(i).getSpeed();
                }
                else if (grid.getSquares()[y / 40][(x + 40) / 40] == GRID.PATH || grid.getSquares()[y / 40][(x + 40) / 40] == GRID.FINISH) {
                    enemies.get(i).direction = Enemy.right;
                } else if (grid.getSquares()[y / 40][(x - 40) / 40] == GRID.PATH || grid.getSquares()[y / 40][(x - 40) / 40] == GRID.FINISH) {
                    enemies.get(i).direction = Enemy.left;
                }
            } else if (enemies.get(i).direction == Enemy.down) {
                if (grid.getSquares()[(y + 40) / 40][x / 40] == GRID.PATH || grid.getSquares()[(y + 40) / 40][x / 40] == GRID.FINISH) {
                    enemies.get(i).Y += enemies.get(i).getSpeed();
                }
                else if (grid.getSquares()[y / 40][(x + 40) / 40] == GRID.PATH || grid.getSquares()[y / 40][(x + 40) / 40] == GRID.FINISH) {
                    enemies.get(i).direction = Enemy.right;
                } else if (grid.getSquares()[y / 40][(x - 40) / 40] == GRID.PATH || grid.getSquares()[y / 40][(x - 40) / 40] == GRID.FINISH) {
                    enemies.get(i).direction = Enemy.left;
                }
            }
        }
    }*/

    public void checkEnemyFinished() {
        for (int i = 0; i < enemies.size(); i++) {
            int x = enemies.get(i).X;
            int y = enemies.get(i).Y;
            if (grid.getSquares()[y / 40][(x) / 40] == GRID.FINISH) {
                Shop.health -= enemies.get(i).getDamage();
                enemies.remove(i);
            }
        }
    }


    public void draw(Graphics g) {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).enemySpawned) {
                g.drawImage(basicEnemy, enemies.get(i).X, enemies.get(i).Y, null);
            }

        }
    }
}
