PATH_TO_PLUGIN=/home/treeleaf/Downloads/protoc-gen-grpc-java-1.28.1-linux-x86_64.exe

SRC_DIR=src/main/proto
DST_DIR=src/main/java

protoc --plugin=protoc-gen-grpc-java=$PATH_TO_PLUGIN -I=$SRC_DIR --java_out=$DST_DIR --grpc-java_out=$DST_DIR $SRC_DIR/Example.proto