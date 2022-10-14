#import matplotlib
import pandas
from sklearn import tree
from sklearn.tree import DecisionTreeClassifier
from sklearn.model_selection import cross_validate
from sklearn.metrics import recall_score
#import matplotlib.pyplot as plt

all_data = pandas.read_csv("spambase.data", header=None)

X = all_data.iloc[:len(all_data.index),0:57]
y = all_data.iloc[:len(all_data.index),57]

dtree = DecisionTreeClassifier()

scoring = ['precision_macro', 'recall_macro', 'f1_macro']
cv_results = cross_validate(dtree, X, y, cv=10, return_estimator=True, scoring=scoring)
print (sorted(cv_results.keys()) )
estims_scores = list(cv_results['test_f1_macro'])
print ("oceny kolejnych klasyfikatorów według miary F1 :")
print (estims_scores)


#Do narysowania 'najlepszego' drzewa decyzyjnego
#idx_of_best_estim = estims_scores.index( max(estims_scores) )
#best_estim = cv_results['estimator'][idx_of_best_estim]

#plt.figure(figsize=(100,100))
#tree.plot_tree(best_estim, fontsize=10)

#plt.savefig("best_tree.png")
