from setuptools import setup, find_packages
import bitcoin_notary

setup(
    name = 'bitcoin-notary',
    version = bitcoin_notary.__version__,
    description='Bitcoin notary service.',
    long_description='This client uses the blockchain of Bitcoin to verify  the existance of a given document (sha256 sum) at a certain point of time.',
    maintainer='Harald Schilly',
    maintainer_email='harald.schilly@gmail.com',
    url='http://haraldschilly.github.com/bitnotar/',
    classifiers=[
        #'Development Status :: 4 - Beta',
        'Development Status :: 2 - Pre-Alpha',
        'Environment :: Console',
        'Environment :: Web Environment',
        'Programming Language :: Python',
        'License :: OSI Approved :: Apache Software License',
        'Topic :: Office/Business :: Financial',
        'Topic :: Security :: Cryptography'
    ],
    install_requires = [
      'docutils >= 0.3',
      'ecdsa >= 0.8',
    ],
    packages = find_packages("src"),
    package_dir = {'':'src'}
)


