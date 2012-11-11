# -*- coding: utf8 -*-

def sha256sum(fn):
  '''
  Calculates the SAH 256 sum of the given byte-vector in the file ``fn``.
  '''
  from hashlib import sha256
  chunk_size = 1024 * 1024 # 1 MB
  cs = sha256()
  with open(fn, "rb") as f:
     while True:
       byte = f.read(chunk_size)
       if not byte: break
       cs.update(byte)
  return cs.hexdigest()

verify_services = [ 'blockchain', 'local' ]

def verify(hashsum):
  '''
  This function verifies the given ``hashsum``.
  '''
  return None


def testify(data):
  '''
  For the given ``data``, calculate the Bitcoin address where funds
  shoule be sent to.
  '''
  csum = sha256sum(data)
  print "Checksum: %s" % csum
  return csum

