#!/bin/bash

sudo lsof -i :8004 | awk 'NR>1 {print $2}' | xargs -r kill -9
sudo lsof -i :8001 | awk 'NR>1 {print $2}' | xargs -r kill -9
sudo lsof -i :8002 | awk 'NR>1 {print $2}' | xargs -r kill -9
sudo lsof -i :8003 | awk 'NR>1 {print $2}' | xargs -r kill -9