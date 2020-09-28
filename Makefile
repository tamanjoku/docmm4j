include ./Makefile.constants
include ./Makefile.build

##########################
# App Build and Run Rules
##########################
compile:
	$(MAKE) -f $(BUILD_MAKEFILE) compile

build_app_images:
	$(MAKE) -f $(BUILD_MAKEFILE) build_app_images

app_up:
	$(MAKE) -f $(BUILD_MAKEFILE) app_up

app_down:
	$(MAKE) -f $(BUILD_MAKEFILE) app_down