
public class Solution {

  public int solve(int[][] points, int k) {
    return findTotalGroupsOfPoints(points, k);
  }

  public int findTotalGroupsOfPoints(int[][] points, int k) {

    UnionFind unionFind = new UnionFind(points.length);
    int totalGroupsOfPoints = points.length;

    for (int i = 0; i < points.length; i++) {
      for (int j = 1 + i; j < points.length; j++) {
        if (k >= calculateEuclideanDistance(points[i], points[j])) {

          int pointOne = unionFind.findParent(i);
          int pointTwo = unionFind.findParent(j);

          if (pointOne != pointTwo) {
            unionFind.union(pointOne, pointTwo);
            totalGroupsOfPoints--;
          }
        }
      }
    }
    return totalGroupsOfPoints;
  }

  public double calculateEuclideanDistance(int[] pointOne, int[] pointTwo) {

    int x_01 = pointOne[0];
    int y_01 = pointOne[1];

    int x_02 = pointTwo[0];
    int y_02 = pointTwo[1];

    return Math.sqrt(Math.pow((x_01 - x_02), 2) + Math.pow((y_01 - y_02), 2));
  }
}

class UnionFind {

  int totalPoints;
  Subset[] subsets;

  public UnionFind(int totalPoints) {

    this.totalPoints = totalPoints;
    this.subsets = new Subset[totalPoints];

    for (int i = 0; i < totalPoints; i++) {
      subsets[i] = new Subset();
      subsets[i].parent = i;
    }
  }

  public int findParent(int point) {

    if (subsets[point].parent != point) {
      subsets[point].parent = findParent(subsets[point].parent);
    }
    return subsets[point].parent;
  }

  public void union(int pointOne, int pointTwo) {

    if (subsets[pointOne].rank > subsets[pointTwo].rank) {
      subsets[pointTwo].parent = pointOne;
    } else if (subsets[pointOne].rank < subsets[pointTwo].rank) {
      subsets[pointOne].parent = pointTwo;
    } else {
      subsets[pointTwo].parent = pointOne;
      subsets[pointOne].rank++;
    }
  }
}

class Subset {
  int parent;
  int rank;
}
