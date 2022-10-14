#import matplotlib
import pandas
from sklearn import tree
from sklearn.tree import DecisionTreeClassifier
from sklearn.model_selection import cross_validate
#import matplotlib.pyplot as plt

all_data = pandas.read_csv("spambase.data", header=None)

X = all_data.iloc[:len(all_data.index),0:57]
y = all_data.iloc[:len(all_data.index),57]

#print(X)
#print(y)

dtree = DecisionTreeClassifier()
#dtree = dtree.fit(X, y)

cv_results = cross_validate(dtree, X, y, cv=10, return_estimator=True)
#print (sorted(cv_results.keys()) )
estims_scores = list(cv_results['test_score'])
print ("oceny kolejnych klasyfikatorów:")
print (estims_scores)
#idx_of_best_estim = estims_scores.index( max(estims_scores) )
#best_estim = cv_results['estimator'][idx_of_best_estim]

#plt.figure(figsize=(100,100))
#tree.plot_tree(best_estim, fontsize=10)

#plt.savefig("best_tree.png")
