#!/usr/bin/env python
# minimal flask server

from flask import Flask
app = Flask(__name__)

@app.route("/verify/<addr>")
def verify(addr):
  from notary import verify
  t, ts, addr = verify(addr)
  if t:
    res = "Success: %s for address <a href='https://blockchain.info/address/%s'>%s</a>" % (ts, addr, addr)
  else:
    res = "Unsuccessfull. Bitcoin address is <a href='https://blockchain.info/address/%s'>%s</a>" % (addr, addr)
  return res

if __name__ == "__main__":
  app.run()
