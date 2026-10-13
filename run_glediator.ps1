$ErrorActionPreference = "Stop"
Set-Location -LiteralPath $PSScriptRoot

$venvPython = Join-Path $PSScriptRoot ".venv\Scripts\python.exe"
if (-not (Test-Path -LiteralPath $venvPython)) {
    py -m venv .venv
    if ($LASTEXITCODE -ne 0) {
        throw "Creating the virtual environment failed (exit code $LASTEXITCODE)."
    }
}

. .\.venv\Scripts\Activate.ps1

python -m pip install -e .
if ($LASTEXITCODE -ne 0) {
    throw "Installing Glediator failed (exit code $LASTEXITCODE)."
}

python main.py
exit $LASTEXITCODE
