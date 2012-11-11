from setuptools import setup, find_packages
setup(
    name = 'bitcoin-notary',
    version = '0.1',
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
    packages = find_packages("src"),
    package_dir = {'':'src'}
)


