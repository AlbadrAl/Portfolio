import random

from bridges import *

class SpreadingFire(NonBlockingGame):
    #Sets the size of the board
    gridColumns = 30
    gridRows = 30 

    #Probability of fire spreading, feel free to play with this value
    spreadingProbability = 50

    #How many trees to plant proportionally
    forest_density = 100

    #integer values to represent if a cell is empty, has a fire, or has a tree
    FIRE = 2
    TREE = 1
    EMPTY = 0

    #Setting variables to store different colors to make it cleaner to code
    fireColor = NamedColor.red
    treeColor = NamedColor.green
    emptyColor = NamedColor.black

    #Setting variables to store different symbols to make it cleaner to code
    fireSymbol = NamedSymbol.campfire
    treeSymbol = NamedSymbol.triangle_up
    emptySymbol = NamedSymbol.none

    #If a cell has a tree, should it catch on fire?
    def cell_contains_forest(self, col, row):
        #Gets a random number to determine if the fire should burn out
        probabilityTest = random.randrange(0, 100)
        nearbyFire = False

        #Tests the cells above, below, to the left, and to the right to see if there is a fire
        #TODO: Test the cells above, below, left, and right of col, row to see if there is a fire
        #Hint: You can test all four in one if statement
        #Hint: Make sure the col or row is within the bounds of 0-29 before you add/subtract
        #Hint: What value is fire set to up above to make our coding easier?
        for dr, dc in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
            new_row, new_col = row + dr, col + dc

            #TODO: if the result is true, set nearbyFire to True
            if 0 <= new_row < self.gridRows and 0 <= new_col < self.gridColumns and self.treeMap[new_row][new_col] == self.FIRE:
                nearbyFire = True

        #TODO:If there is a fire nearby and the random probably we found is less than the
        #spreadingProbability in the class variables, ignite the cell
          #Hint: is there a function that can help you ignite a particular col, row?
            if nearbyFire and probabilityTest < self.spreadingProbability:
                self.ignite(col, row)
    #The cell has a fire, so should it burn out?      
    def cell_contains_fire(self, col, row):
        #Get a random number
        prob_test = random.randrange(0, 100)
        if prob_test > self.forest_density:
           self.burn_out(col, row)
           #TODO: get a random number between 0 and 100
        #TODO: If the random number is greater than the forest density, let the fire burn out
            #Hint: Is there a function that can help a col, row burn out?

    #Sets a cell on fire
    def ignite(self, col, row):
      #TODO: draw_symbol for the col, row, using the firesymbol and fire color
      #TODO: set the self.treeMap at the col, row to self.FIRE
      #Hint: look at the burn_out method
        self.draw_symbol(col, row, self.fireSymbol, self.fireColor)
        self.treeMap[row][col] = self.FIRE


    #Burns out a fire
    def burn_out(self, col, row):
        self.set_bg_color(col, row, self.emptyColor)
        self.draw_symbol(col, row, self.emptySymbol, self.emptyColor)
        self.treeMap[col][row] = self.EMPTY

    #Creates a tree
    def grow_tree(self, col, row):
      #TODO: draw_symbol for the col, row, using the tree symbol and tree color
      #TODO: set the self.treeMap at the col, row to self.TREE
        self.draw_symbol(col, row, self.treeSymbol, self.treeColor)
        self.treeMap[row][col] = self.TREE

    def game_loop(self):
      #Iterates through the columns and rows
      #If the item in a given cell (col, row) is a tree, call the function 
      #cell_contains_forest
      #If the item in a given cell (col, row) is a fire, call the function 
      #cell_contains_fire
      #TODO: loop through the columns
        #TODO: loop through the rows
          #TODO: If the treeMap at col, row equals self.TREE, call cell_contains_forest for that col, row
          #TODO: If the treeMap at col, row equals self.FIRE, call cell_contains_fire for that col, row

        for col in range(self.gridColumns):
                    for row in range(self.gridRows):
                        if self.treeMap[row][col] == self.TREE:
                            self.cell_contains_forest(col, row)
                        elif self.treeMap[row][col] == self.FIRE:
                            self.cell_contains_fire(col, row)

    def initialize(self):
        self.simulation_one()
        #self.simulation_two()

    def __init__(self, assid, login, apikey, cols, rows):
        super(SpreadingFire, self).__init__(assid, login, apikey, cols, rows)

        super(SpreadingFire, self).set_title("Spreading Fire")
        super(SpreadingFire, self).set_description("Simulation of fire in a forest.")

        self.treeMap = [[0 for i in range(30)] for j in range(30)]


    #Creates a forest within an inner square of empty cells with a fire in the center
    def simulation_one(self):
        self.treeMap = [[0 for i in range(30)] for j in range(30)]
        # This grid desplays a full forest with a fire in the center.
        for col in range(self.gridColumns):
            for row in range(self.gridRows):
                if (col == 3 or row == 3 or col == 30 - 4 or row == 30 - 4):
                    self.burn_out(col, row)
                else:
                    self.grow_tree(col, row)

                if (row == int(self.gridRows / 2) and col == int(self.gridColumns / 2)):
                    self.ignite(col, row)

    def simulation_two(self):
        self.treeMap = [[0 for i in range(30)] for j in range(30)]
        # This grid desplays a full forest with a fire in the center.
        for col in range(self.gridColumns):
            for row in range(self.gridRows):
                self.grow_tree(col, row)

                self.burn_out(row, row)
                self.burn_out(row, self.gridRows - (row+1))

                if (row==0 and col==self.gridColumns/2 or
                   col==0 and row==self.gridRows/2 or
                   row==self.gridRows-1 and col==self.gridColumns / 2 or
                   col==self.gridColumns-1 and row==self.gridRows / 2):

                    self.ignite(col, row)


def main():
    my_game = SpreadingFire(214, "Albadr", "1317503500836", 30, 30)
    my_game.start()


if __name__ == '__main__':
    main()


