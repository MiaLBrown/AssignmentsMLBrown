from flask import Flask, request

# create the Flask app
app = Flask("Example")


@app.route('/query-example')
def query_example():
    # if key doesn't exist, returns None
    language = request.args.get('language')

    # if key doesn't exist, returns a 400, bad request error
    framework = request.args['framework']

    # if key doesn't exist, returns None
    website = request.args.get('website')

    return '''
              <h1>The language value is: {}</h1>
              <h1>The framework value is: {}</h1>
              <h1>The website value is: {}'''.format(language, framework, website)


@app.route('/form-example', methods=['GET', 'POST'])
def form_example():
    # handle the POST request
    if request.method == 'POST':
        request.form.get("language")
        request.form.get('framework')
        return 'POST'
    # otherwise handle the GET request
    return ' GET'


# curl -Method POST -Uri http://localhost:5000/form-example -ContentType "application/json"
# -body '{language: "python", framework: "flask"}'

@app.route('/json-example', methovsds=['POST'])
def json_example():
    request_data = request.get_json()
    language = request_data['language']
    framework = request_data['framework']

    # two keys are needed because of the nested object
    python_version = request_data['version_info']['python']

    # an index is needed because of the array
    example = request_data['examples'][0]

    boolean_test = request_data['boolean_test']

    return '''
           The language value is: {}
           The framework value is: {}
           The Python version is: {}
           The item at index 0 in the example list is: {}
           The boolean value is: {}'''.format(language, framework,
                                              python_version, example, boolean_test)


if __name__ == '__main__':
    # run app in debug mode on port 5000
    app.run(debug=True, port=5000)
