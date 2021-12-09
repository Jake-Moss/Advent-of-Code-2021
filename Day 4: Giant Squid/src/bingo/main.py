#!/usr/bin/env python3
import itertools as it


class board():
    def __init__(self, boardstr: list[str]) -> None:
        self.board = [[int(x) for x in y.split()] for y in boardstr]
        self.marked = []
        self.won = False

    def play(self, num: int):
        self.marked.append(num)

    def checkWin(self):
        if self.won is False:
            for col in self.board:
                if all([x in self.marked for x in col]):
                    return True
            for row in zip(*self.board):
                if all([x in self.marked for x in row]):
                    return True
            return False
        else:
            return True

    def __str__(self) -> str:
        result = ""
        for x in self.board:
            result += str(x) + "\n"
        return result

    def flatten(self):
        return [item for sublist in self.board for item in sublist]

    def sumBoard(self):
        return sum(self.flatten())


played = []

def part1(boards: list[board], nums: list[int]):
    for x in nums:
        played.append(x)
        for b in boards:
            b.play(x)
            if b.checkWin():
                return b.sumBoard() - sum(x for x in played if x in b.flatten())

def part2(boards: list[board], nums: list[int]):
    won = 0
    for x in nums:
        played.append(x)
        for b in boards:
            b.play(x)
            if b.won is False:
                if b.checkWin():
                    b.won = True
                    won += 1
                    if len(boards) - won == 0:
                        print(b)
                        return b.sumBoard() - sum(x for x in played if x in b.flatten())

if __name__ == "__main__":
    with open('input') as f, open('sample') as sample:
        sample = [x.strip() for x in sample.readlines()]
        f = [x.strip() for x in f.readlines()]


        nums = [int(x) for x in f[0].split(',')]
        boards = [board(x) for x in [list(group) for k, group in it.groupby(f[2:], bool) if k]]

        played = []
        print(part1(boards, nums) , played[-1])
        played = []
        print(part2(boards, nums) , played[-1])
