package se.liu.ida.andze132.tddd78.javaproject;

public final class Maps
{

    private Maps() {}

    public static SquareType[][] getMap(int n) {
	switch (n) {
	    case 1:
		return map1();
	    default:
		throw new IllegalArgumentException("Invalid input n");

	}
    }

    public static SquareType[][] map1() {
	SquareType[][] squares = new SquareType[][] {
		{ SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS,
			SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS,
			},
		{ SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS,
			SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS,
			},
		{ SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS,
			SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS,
			 },
		{ SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS,
			SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.TOWER, SquareType.GRASS,
			},
		{ SquareType.START, SquareType.PATH, SquareType.PATH, SquareType.PATH, SquareType.PATH, SquareType.PATH,
			SquareType.PATH, SquareType.PATH, SquareType.PATH, SquareType.PATH, SquareType.FINISH
			},
		{ SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS,
			SquareType.TOWER, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS,
			},
		{ SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS,
			SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS,
			 },
		{ SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS,
			SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS,
			},
		{ SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS,
			SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS, SquareType.GRASS,
			}
		 };
	return squares;
    }
}
