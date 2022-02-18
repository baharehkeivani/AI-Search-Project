class Cell:
    def __init__(self, row: int, col: int, path: list, path_value: int, goal_value: int):
        self.row = row
        self.col = col
        self.path_value = path_value
        self.path = path
        self.goal_value = goal_value

    def __eq__(self, other):
        if type(other) != Cell:
            return False
        if other.row == self.row and other.col == self.col:
            return True
        return False

    def __get_sorted_path(self) -> list:
        sorted_path = self.path.copy()
        sorted_path.sort(key=lambda x: (x.row, x.col))
        return sorted_path

    def __hash__(self):
        return ' '.join([str(x) for x in self.__get_sorted_path()]) + '###' + str(self.path_value) + ' ' + str(
            self.goal_value)
