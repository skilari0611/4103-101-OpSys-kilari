
import cmd
import argparse
import multiprocessing as mp
from os.path import dirname, realpath
import sys

sys.path.append(dirname(realpath(__file__)))

from cd import chd

def wrap(f):
	def _wrapped(*args):
		_self = args[0]
		rest = args[1]
		background = rest.endswith('&')
		arguments = args
		out = f(*arguments)
	return _wrapped

class Shell(cmd.Cmd):

    prompt = '% '
    def __init__(self):
        super().__init__()

    @wrap
    def do_cd(self, arg):
        """
        change to a directory
        usage: cd directory
        if directory is
        ~  change to home-directory
        .. change to parent directory
        """
        return chd(arg)


    def do_exit(self, args):
        exit()


if __name__ == '__main__':
    Shell().cmdloop()