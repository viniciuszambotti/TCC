from flask import Flask
from flask_cors import CORS
from runipy.notebook_runner import NotebookRunner
from IPython.nbformat.current import read
from IPython.nbformat.current import write


app = Flask(__name__)
CORS(app)

@app.route('/user/<script_name>')
def exec_nb(script_name):
    print(script_name)
    notebook = read(open("C:/Users/Vinicius Zambotti/Documents/codes/TCC/tcc-python/notebooks/" + script_name), 'json')
    r = NotebookRunner(notebook)
    r.run_notebook()
    graphs = []
    for cell in r.nb.worksheets[0].cells:
        if(len(cell.outputs) > 0):
            graphs.append(cell.outputs[0].png)
        resposta = ','.join(str(e) for e in graphs)

    return resposta


if __name__ == "__main__":
    app.run(debug=True,port=8084)
