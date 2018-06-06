from runipy.notebook_runner import NotebookRunner
from IPython.nbformat.current import read
from IPython.nbformat.current import write

def exec_nb(script_name):
    notebook = read(open("C:/Users/Vinicius Zambotti/Documents/codes/TCC/tcc-python/notebooks/" + script_name), 'json')
    r = NotebookRunner(notebook)
    r.run_notebook()
    graphs = []
    for cell in r.nb.worksheets[0].cells:
        if(len(cell.outputs) > 0):
            graphs.append(cell.outputs[0].png)

    return graphs;
