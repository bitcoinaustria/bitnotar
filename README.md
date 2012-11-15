Bitnotar
========

Bitnotar is a [Notary Service](http://en.wikipedia.org/wiki/Notary) 
for witnessing the existance of a certain document.
This service allows to "testify" this at a certain point in time.
Later on, the existance can be proven based on the document
(or the "hashsum" of the document) and an estimated (+/- 1 hour max.)
time-stamp is returned.
Most notably, this doesn't rely on a [trusted thrid party](http://en.wikipedia.org/wiki/Trusted_Third_party).

Technically, the idea is to use the block-chain of [Bitcoin](http://bitcoin.org)
as a distributed time-stamp protocol. This works, because each new block
in this block-chain is created about every 10 minutes.
Each block references the predecessor and inside each block, every
transaction is referenced.

The process of testifying the existance of a document is done by sending a
very small amount of Bitcoins to the public Bitcoin address derived from the
hashsum of the document itself.

By disclosing the procedure of how this derivation is done (basically, the
[SHA-256](http://en.wikipedia.org/wiki/Sha-256) or some other hash function),
everyone is able to verify this by means of a simple lookup in the block-chain.

The essence of the actual "proof" is the amount of computation needed to create
the block-chain. I.e. if you discover, that the derived public Bitcoin address
associated with a given document is N blocks deep in the block-chain, N
blocks have been mined since then. Mining each block happens at a speed of
about one every 10 minutes, where this process is self adjusting and (until now)
has never been broken or hacked.

Features
--------

The given application has (or should have) these two basic functions:

1. **Testify**: Derive the public Bitcoin address and initiate a Bitcoin transaction.
2. **Verify**: Derive the public Bitcoin address and look it up at a service like
   [blockchain.info](http://blockchain.info) or [blockexplorer](http://blockexplorer.com).
   Return the associated time-stamp (and maybe also look up the time-stamps of the surrounding blocks
   to verify the time-stamp). Additionally, local lookup mechanisms could be implemented.

Links
-----

[Presentation](https://docs.google.com/presentation/pub?id=1qS8zFR5TLxRtVAV6qzBOTHMy4_wlWh69t_FQMTmpg48&start=false&loop=false&delayms=3000)
