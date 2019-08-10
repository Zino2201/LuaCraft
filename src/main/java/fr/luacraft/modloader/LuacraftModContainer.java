package fr.luacraft.modloader;

import com.google.common.collect.ImmutableList;
import com.google.common.eventbus.EventBus;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.versioning.ArtifactVersion;
import cpw.mods.fml.common.versioning.DefaultArtifactVersion;
import cpw.mods.fml.common.versioning.VersionRange;

import java.io.File;
import java.security.cert.Certificate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Luacraft mod container
 * @author Zino
 */
public class LuacraftModContainer implements ModContainer
{
    private ArtifactVersion processedVersion;
    private LuacraftModInfo modInfo;

    protected void setModInfo(LuacraftModInfo mi)
    {
        modInfo = mi;
    }

    @Override
    public void bindMetadata(MetadataCollection mc)
    {

    }

    public LuacraftModInfo getModInfo()
    {
        return modInfo;
    }

    @Override
    public List<ArtifactVersion> getDependants()
    {
        return Collections.emptyList();
    }

    @Override
    public List<ArtifactVersion> getDependencies()
    {
        return Collections.emptyList();
    }

    @Override
    public Set<ArtifactVersion> getRequirements()
    {
        return Collections.emptySet();
    }

    @Override
    @Deprecated
    public ModMetadata getMetadata()
    {
        return null;
    }

    @Override
    public Object getMod()
    {
        return null;
    }

    @Override
    public String getModId()
    {
        return modInfo.getModId();
    }

    @Override
    public String getName()
    {
        return modInfo.getName();
    }

    @Override
    public String getSortingRules()
    {
        return "";
    }

    @Override
    public File getSource()
    {
        return null;
    }

    @Override
    public String getVersion()
    {
        return modInfo.getVersion();
    }

    @Override
    public boolean matches(Object mod)
    {
        return false;
    }

    @Override
    public void setEnabledState(boolean enabled)
    {

    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller)
    {
        return false;
    }

    @Override
    public ArtifactVersion getProcessedVersion()
    {
        if (processedVersion == null)
        {
            processedVersion = new DefaultArtifactVersion(getModId(), getVersion());
        }
        return processedVersion;
    }

    @Override
    public boolean isImmutable()
    {
        return false;
    }

    @Override
    public String getDisplayVersion()
    {
        return modInfo.getVersion();
    }

    @Override
    public VersionRange acceptableMinecraftVersionRange()
    {
        return Loader.instance().getMinecraftModContainer().getStaticVersionRange();
    }

    @Override
    public Certificate getSigningCertificate()
    {
        return null;
    }

    @Override
    public String toString()
    {
        return modInfo != null ? getModId() : "Luacraft Mod Container ("+modInfo.getModId()+") @" + System.identityHashCode(this);
    }

    @Override
    public Map<String, String> getCustomModProperties()
    {
        return EMPTY_PROPERTIES;
    }
    @Override
    public Class<?> getCustomResourcePackClass()
    {
        return null;
    }

    @Override
    public Map<String, String> getSharedModDescriptor()
    {
        return null;
    }

    @Override
    public Disableable canBeDisabled()
    {
        return Disableable.NEVER;
    }

    @Override
    public String getGuiClassName()
    {
        return null;
    }

    @Override
    public List<String> getOwnedPackages()
    {
        return ImmutableList.of();
    }

    public String getLuacraftVersion()
    {
        return modInfo.getLuacraftVersion();
    }
}
