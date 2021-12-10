#!/usr/bin/env python3

if __name__ == "__main__":
    with open('input') as f, open('sample') as sample:
        sample = [int(y) for y in sample.readline().strip().split(',')]
        f = [int(y) for y in f.readline().strip().split(',')]
