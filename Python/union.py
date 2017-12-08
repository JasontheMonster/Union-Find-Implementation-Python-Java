from GraphAPI import Graph
from heapq import *
from networkx.algorithms import *
import networkx as nx 
import random
import time

class Union(object):
	def __init__(self,n):
		self.size = n
		self.uf = [] #array to store parent 
		self.rank = [] #array for ranks
		#initialize parent as itself
		for i in range (self.size):
			self.uf.append(i) #initiate its parent as itself
			self.rank.append(1) #initial rank as 1 
	#find operation
	def find(self,x):
		if self.uf[x]==x: #if parent is itself, return itself
			return x
		else:
			return self.find(self.uf[x]) #return parent of node x
	#union operation
	def union(self,x,y):
		self.uf[self.find(x)]=self.find(y) #set the parent of x as the parent of y
	#find with path compression
	def findPC(self,x):
		if self.uf[x]!=self.uf[self.uf[x]]: #if x's parent is not its parent's parent
			self.uf[x]=self.findPC(self.uf[x]) #set it to be that to make the tree shallow
		return self.uf[x]
	#union with rank, set the shallower tree's parent to deeper tree
	def unionRank(self,x,y):
		xx=self.findPC(x) #parent of x
		yy=self.findPC(y) #parent of y
		if self.rank[xx]>self.rank[yy]: #if rank of x's parent is higher
			self.uf[yy]=xx #set y's parent as x's parent
		elif self.rank[xx]<self.rank[yy]: 
			self.uf[xx]=yy
		else: #self.rank[xx]==self.rank[yy]:
			self.uf[yy]=xx #pick one to set as parent
			self.rank[xx]+=1 #increase the chosen one's rank by 1 since it grew
		return True

#test for runtime
if __name__ == "__main__":
	n = 9000
	test = Union(n)
	starttime = time.time()
	for i in range (n):
		x= random.randint(0,n-1)
		y= random.randint(0,n-1)
		z= random.randint(0,n-1)
		test.union(x,y)
		test.find(z)
	print time.time()-starttime
'''
	if test.find(4) == test.find(0):
		print "Yes"
	else:
		print "No"

	if test.find(1) == test.find(0):
		print "Yes"
	else:
		print "No"'''
